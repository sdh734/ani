package edu.smxy.associationmanagement.mapper;

import edu.smxy.associationmanagement.domain.KeyProject;

import java.util.List;

public interface KeyProjectMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(KeyProject record);

	int insertSelective(KeyProject record);

	KeyProject selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(KeyProject record);

	int updateByPrimaryKey(KeyProject record);

	KeyProject selectByRecord(KeyProject record);

	List<KeyProject> getAllRunningKeyProjectByAssId(int assid);
}
