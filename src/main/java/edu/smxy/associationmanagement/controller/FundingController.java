package edu.smxy.associationmanagement.controller;

import edu.smxy.associationmanagement.domain.Funding;
import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.services.funding.FundingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping({"/addfunding"})
    public JSONResult addFunding(Funding funding) {
        int record = fundingService.insert(funding);
        if (record > 0) {
            return JSONResult.build(200, "ok", null);
        } else {
            return JSONResult.build(500, "error", null);
        }
    }

    @PostMapping({"/getAllFundingByAssid"})
    public JSONResult getAllFundingByAssid(int assid) {
        List<Funding> fundings = fundingService.getAllFundingByAssid(assid);
        if (fundings.size() > 0) {
            return JSONResult.build(200, "ok", fundings);
        } else {
            return JSONResult.build(500, "没有记录", null);
        }
    }

    @PostMapping({"/updateFunding"})
    public JSONResult updateFunding(Funding funding) {
        int record = fundingService.updateByPrimaryKey(funding);
        if (record > 0) {
            return JSONResult.build(200, "ok", null);
        } else {
            return JSONResult.build(500, "修改失败", null);
        }
    }

    @PostMapping({"/deleteFundingById"})
    public JSONResult deleteFundingById(int id) {
        int record = fundingService.deleteByPrimaryKey(id);
        if (record > 0) {
            return JSONResult.build(200, "ok", null);
        } else {
            return JSONResult.build(500, "error", null);
        }
    }
}
