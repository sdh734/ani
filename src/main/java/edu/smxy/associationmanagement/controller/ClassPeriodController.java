package edu.smxy.associationmanagement.controller;

import com.xuxueli.poi.excel.ExcelExportUtil;
import edu.smxy.associationmanagement.domain.Association;
import edu.smxy.associationmanagement.domain.ClassPeriod;
import edu.smxy.associationmanagement.domain.ClassPeriodResult;
import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.services.association.AssociationService;
import edu.smxy.associationmanagement.services.classperiod.ClassPeriodService;
import edu.smxy.associationmanagement.services.teacher.TeacherService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 指导记录Controller 主要功能:添加 统计 导出到Excel
 */
@ResponseBody
@RestController
@EnableAutoConfiguration
public class ClassPeriodController {
	@Autowired
	ClassPeriodService classPeriodService;
	@Autowired
	AssociationService associationService;
	@Autowired
	TeacherService teacherService;

	/**
	 * 添加指导记录
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping({"/addguide"})
	public JSONResult AddGuide(final HttpServletRequest request) {
		// TODO: 2019/03/13 添加更多详细指导信息-教学内容简介等 参与人数等
		final ClassPeriod classPeriod = new ClassPeriod();
		final String assid = request.getParameter("assid");
		final String guidetime = request.getParameter("guidetime");
		final String guidedate = request.getParameter("guidedate");
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		classPeriod.setClassperiodDate(formatter.parse(guidedate, new ParsePosition(0)));
		classPeriod.setClassperiodTime(Double.valueOf(guidetime));
		classPeriod.setClassperiodAssciation(Integer.valueOf(assid));
		classPeriod.setClassperiodTeacher(
				this.associationService.selectByPrimaryKey(Integer.valueOf(assid)).getTeacher());
		this.classPeriodService.insert(classPeriod);
		return JSONResult.build(200, "ok", null);
	}

	/**
	 * 根据协会id获得所有指导记录
	 *
	 * @param assid
	 * @return
	 */
	@RequestMapping({"/getAllGuideByAssid"})
	public JSONResult getAllGuideByAssid(final String assid) {
		// TODO: 2019/03/13 设置更多筛选条件
		List<ClassPeriodResult> classPeriodResults = getClassPeriodResult(assid);
		return JSONResult.build(200, "ok", classPeriodResults);
	}

	/**
	 * 获得指定协会的指导情况表
	 *
	 * @param assid
	 * @param response
	 */
	@RequestMapping({"/getAllGuideByAssidtoExcel"})
	public JSONResult getAllGuideByAssidtoExcel(
			final String assid, final HttpServletResponse response) {
		// TODO: 2019/03/21 修改excel导出方式 改为使用Alibaba的EasyExcel库
		// TODO: 2019/03/13 修改方法增加按指定时间段内所有的指导情况

		getClassPeriodResult(assid);
		List<ClassPeriodResult> classPeriodResults = getClassPeriodResult(assid);
		final Association association =
				this.associationService.selectByPrimaryKey(Integer.valueOf(assid));
		// 本地目录
		final String filepath =
				"G:\\upload\\guideinfo\\" + association.getAssociationName() + "-指导情况统计.xls";
		// 服务器目录
		// final String filepath = "/www/wwwroot/ass/upload/";
		final String filename = association.getAssociationName() + "-指导情况统计.xls";
		if (classPeriodResults.size() > 0) {
			ExcelExportUtil.exportToFile(classPeriodResults, filepath);
			try (final InputStream inputStream = new FileInputStream(new File(filepath));
				 final OutputStream outputStream = response.getOutputStream()) {
				response.setContentType("application/x-download");
				response.addHeader(
						"Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename, "UTF-8"));
				IOUtils.copy(inputStream, outputStream);
				outputStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return JSONResult.build(200, "ok", "ok");
		} else {
			return JSONResult.build(404, "error", "当前协会无指导历史记录");
		}
	}

	/**
	 * 根据协会id获得指导记录
	 *
	 * @param assid 协会id
	 * @return 指导记录集合
	 */
	private List<ClassPeriodResult> getClassPeriodResult(String assid) {
		final List<ClassPeriod> classPeriods =
				this.classPeriodService.getAllByAssid(Integer.valueOf(assid));
		final List<ClassPeriodResult> classPeriodResults = new ArrayList<>();
		for (final ClassPeriod i : classPeriods) {
			final ClassPeriodResult classPeriodResult = new ClassPeriodResult(i);
			classPeriodResult.setClassperiodAssciation(
					this.associationService
							.selectByPrimaryKey(i.getClassperiodAssciation())
							.getAssociationName());
			classPeriodResult.setClassperiodTeacher(
					this.teacherService.selectByPrimaryKey(i.getClassperiodTeacher()).getTeacherName());
			classPeriodResults.add(classPeriodResult);
		}
		return classPeriodResults;
	}
}
