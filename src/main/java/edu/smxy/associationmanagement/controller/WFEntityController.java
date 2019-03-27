package edu.smxy.associationmanagement.controller;

import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.domain.WFEntity;
import edu.smxy.associationmanagement.services.wfentity.WFEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: associationmanagement
 * @description: 流程实体类Controller
 * @author: SDH
 * @create: 2019-03-27 20:47
 **/
@RestController
@EnableAutoConfiguration
public class WFEntityController {
	@Autowired
	WFEntityService wfEntityService;

	/**
	 * 根据创建人id 和申请类型id 获得所有在传入申请类型 申请时间段内 由传入创建人创建的流程集合
	 *
	 * @param typeid   申请类型id
	 * @param authorid 创建人id
	 * @return
	 */
	@PostMapping({"/getRunningApply"})
	public JSONResult getRunningApply(int typeid, int authorid) {
		List<WFEntity> wfEntities = wfEntityService.getRunningApply(typeid, authorid);
		if (wfEntities.size() > 0) {
			return JSONResult.build(200, "ok", wfEntities);
		} else {
			return JSONResult.build(500, "error", null);
		}
	}
}
