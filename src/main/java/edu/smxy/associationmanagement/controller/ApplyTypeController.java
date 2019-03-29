package edu.smxy.associationmanagement.controller;

import edu.smxy.associationmanagement.domain.ApplyType;
import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.services.applytype.ApplyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;

/**
 * @program: associationmanagement
 * @description: 申请类型Controller
 * @author: SDH
 * @create: 2019-03-27 20:45
 */
@RestController
@EnableAutoConfiguration
public class ApplyTypeController {
  @Autowired
  ApplyTypeService applyTypeService;

  /**
   * 判断申请类型是否在申请时间内
   *
   * @param applytype 申请类型id
   * @return
   */
  @PostMapping({"/applyIsInTime"})
  public JSONResult isInTime(int applytype) {
    ApplyType record = applyTypeService.isInTime(applytype);
    if (record != null) {
      return JSONResult.build(200, "ok", record);
    } else {
      return JSONResult.build(500, "error", null);
    }
  }

  /**
   * 管理员设置申请发起时间段
   *
   * @param startTime 起始时间
   * @param endTime   结束时间
   * @param applyType 申请类型
   * @return
   */
  @PostMapping({"/setApplyTime"})
  public JSONResult setApplyTime(String startTime, String endTime, int applyType) {
    ApplyType applyType1 = applyTypeService.selectByPrimaryKey(applyType);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    applyType1.setStartTime(formatter.parse(startTime, new ParsePosition(0)));
    applyType1.setEndTime(formatter.parse(endTime, new ParsePosition(0)));
    int record = applyTypeService.updateByPrimaryKey(applyType1);
    if (record > 0) {
      return JSONResult.build(200, "ok", null);
    } else {
      return JSONResult.build(500, "error", null);
    }
  }
}
