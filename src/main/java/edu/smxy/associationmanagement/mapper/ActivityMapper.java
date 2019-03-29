package edu.smxy.associationmanagement.mapper;

import edu.smxy.associationmanagement.domain.Activity;

import java.util.List;

public interface ActivityMapper {
	int deleteByPrimaryKey(final Integer id);

	int insert(final Activity record);

	int insertSelective(final Activity record);

	Activity selectByPrimaryKey(final Integer id);

	int updateByPrimaryKeySelective(final Activity record);

	int updateByPrimaryKey(final Activity record);

	List<Activity> getAllActivityByAssid(final Integer assid);

	List<Activity> getAllActivity();
}
