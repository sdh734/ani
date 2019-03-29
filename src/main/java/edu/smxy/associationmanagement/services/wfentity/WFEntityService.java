package edu.smxy.associationmanagement.services.wfentity;

import edu.smxy.associationmanagement.domain.WFEntity;

import java.util.List;

/**
 * @program: associationmanagement
 * @description: 流程实体类Service
 * @author: SDH
 * @create: 2019-03-27 20:12
 */
public interface WFEntityService {
	int deleteByPrimaryKey(Integer id);

	int insert(WFEntity record);

	int insertSelective(WFEntity record);

	WFEntity selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(WFEntity record);

	int updateByPrimaryKey(WFEntity record);

	List<WFEntity> getRunningApply(int typeid, int authorid);

	List<WFEntity> getRunningApplyAdmin(int typeid, int status);
}
