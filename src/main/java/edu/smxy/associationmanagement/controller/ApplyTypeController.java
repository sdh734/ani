package edu.smxy.associationmanagement.controller;

import edu.smxy.associationmanagement.domain.ApplyType;
import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.services.applytype.ApplyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
