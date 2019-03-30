package edu.smxy.associationmanagement.services.keyproject;

import edu.smxy.associationmanagement.domain.KeyProject;

import java.util.List;

/**
 * @program: associationmanagement
 * @description: 重点立项Service
 * @author: SDH
 * @create: 2019-03-27 19:49
 */
public interface KeyProjectService {
	int deleteByPrimaryKey(Integer id);

	int insert(KeyProject record);

	int insertSelective(KeyProject record);

	KeyProject selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(KeyProject record);

	int updateByPrimaryKey(KeyProject record);

	KeyProject selectByRecord(KeyProject record);

	List<KeyProject> getAllRunningKeyProjectByAssId(int assid);
}
