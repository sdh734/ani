package edu.smxy.associationmanagement.controller;

import edu.smxy.associationmanagement.domain.Funding;
import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.domain.chartjs.ChartRequest;
import edu.smxy.associationmanagement.domain.chartjs.ChartResponse;
import edu.smxy.associationmanagement.services.funding.FundingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: associationmanagement
 * @description: 会费管理Controller
 * @author: SDH
 * @create: 2019-03-23 15:17
 */
@RestController
@ResponseBody
@EnableAutoConfiguration
public class FundingController {
	@Autowired
	FundingService fundingService;

	/**
	 * 添加会费记录
	 *
	 * @param request
	 * @return
	 */
	@PostMapping({"/addfunding"})
	public JSONResult addFunding(HttpServletRequest request) {
		Funding funding = getFundingFromRequest(request);
		int record = fundingService.insert(funding);
		if (record > 0) {
			return JSONResult.build(200, "ok", null);
		} else {
			return JSONResult.build(500, "error", null);
		}
	}

	/**
	 * 根据协会id获取所有会费记录
	 *
	 * @param assid
	 * @return
	 */
	@PostMapping({"/getAllFundingByAssid"})
	public JSONResult getAllFundingByAssid(int assid) {
		List<Funding> fundings = fundingService.getAllFundingByAssid(assid);
		if (fundings.size() > 0) {
			return JSONResult.build(200, "ok", fundings);
		} else {
			return JSONResult.build(500, "没有记录", null);
		}
	}

	/**
	 * 更新Funding
	 *
	 * @param request
	 * @return
	 */
	@PostMapping({"/updateFunding"})
	public JSONResult updateFunding(HttpServletRequest request) {
		Funding funding = getFundingFromRequest(request);
		funding.setId(Integer.valueOf(request.getParameter("id")));
		int record = fundingService.updateByPrimaryKey(funding);
		if (record > 0) {
			return JSONResult.build(200, "ok", null);
		} else {
			return JSONResult.build(500, "修改失败", null);
		}
	}

	/**
	 * 根据id删除Funding
	 *
	 * @param id
	 * @return
	 */
	@PostMapping({"/deleteFundingById"})
	public JSONResult deleteFundingById(int id) {
		int record = fundingService.deleteByPrimaryKey(id);
		if (record > 0) {
			return JSONResult.build(200, "ok", null);
		} else {
			return JSONResult.build(500, "error", null);
		}
	}

	/**
	 * 根据request获得Funding对象
	 *
	 * @param request
	 * @return
	 */
	private Funding getFundingFromRequest(HttpServletRequest request) {
		int fundingAssid = Integer.valueOf(request.getParameter("fundingAssid"));
		String fundingName = request.getParameter("fundingName");
		int fundingType = Integer.valueOf(request.getParameter("fundingType"));
		BigDecimal fundingCost =
				BigDecimal.valueOf(Double.valueOf(request.getParameter("fundingCost")));
		String fundingInfo = request.getParameter("fundingInfo");
		String fundingTimeStr = request.getParameter("fundingTime");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Date parse = sd.parse(fundingTimeStr, new ParsePosition(0));
		Funding funding = new Funding();
		funding.setFundingAssid(fundingAssid);
		funding.setFundingCost(fundingCost);
		funding.setFundingInfo(fundingInfo);
		funding.setFundingName(fundingName);
		funding.setFundingTime(parse);
		funding.setFundingType(fundingType);
		return funding;
	}

	/** 下面开始统计功能 主要包含以下功能 1. 统计某协会在某段时间内的支出或者收入记录 2. 统计某协会在某段时间内的收入和支出总计 3. 统计某协会在某段时间内的支出或者收入变化 */

	/**
	 * 1. 统计某协会在某段时间内的支出或者收入记录 3. 统计某协会在某段时间内的支出或者收入变化 返回值一样 前端做相应处理 TODO: 2019/03/26 增加按收入支出分类
	 *
	 * @param record
	 * @return
	 */
	@PostMapping({"/getCount1"})
	public JSONResult getCount1(ChartRequest record) {
		record.setEndTime(record.getEndTime().replace('"', ' ').replace("'", "").trim());
		record.setStartTime(record.getStartTime().replace('"', ' ').replace("'", "").trim());
		List<Funding> fundings = getCountByAssidInTime(record);
		if (fundings.size() > 0) {
			List<String> labels = new ArrayList<>();
			List<String> values = new ArrayList<>();
			for (Funding f : fundings) {
				labels.add(f.getFundingName());
				values.add(f.getFundingCost() + "");
			}
			ChartResponse response = new ChartResponse(labels, values);
			return JSONResult.build(200, "ok", response);
		}
		return JSONResult.build(500, "ok", null);
	}

	/**
	 * 2. 统计某协会在某段时间内的收入和支出总计
	 *
	 * @param record
	 * @return
	 */
	@PostMapping({"/getCount2"})
	public JSONResult getCount2(ChartRequest record) {
		record.setEndTime(record.getEndTime().replace('"', ' ').replace("'", "").trim());
		record.setStartTime(record.getStartTime().replace('"', ' ').replace("'", "").trim());
		List<Funding> fundings = fundingService.getCostCountByAssidInTime(record);
		List<String> labels = new ArrayList<>();
		List<String> values = new ArrayList<>();
		// 1 支出 2 收入
		BigDecimal cost1 = new BigDecimal(0), cost2 = new BigDecimal(0);
		if (fundings.size() > 0) {
			for (Funding f : fundings) {
				if (f.getFundingType() == 0) {
					cost1 = cost1.add(f.getFundingCost());
				} else {
					cost2 = cost2.add(f.getFundingCost());
				}
			}
			labels.add("支出总和");
			values.add(cost1.toString());
			labels.add("收入总和");
			values.add(cost2.toString());
			ChartResponse response = new ChartResponse(labels, values);
			return JSONResult.build(200, "ok", response);
		}
		return JSONResult.build(500, "ok", null);
	}

	/**
	 * 获得指定时间段内 指定协会的所有会费变动信息
	 *
	 * @param record
	 * @return
	 */
	public List<Funding> getCountByAssidInTime(ChartRequest record) {
		return fundingService.getCountByAssidInTime(record);
	}
}
