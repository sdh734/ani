package edu.smxy.associationmanagement.mapper;

import edu.smxy.associationmanagement.domain.User;

import java.util.List;

public interface UserMapper {
	int deleteByPrimaryKey(final Integer id);

	int insert(final User record);

	int insertSelective(final User record);

	User selectByPrimaryKey(final Integer id);

	int updateByPrimaryKeySelective(final User record);

	int updateByPrimaryKey(final User record);

	User query(final User user);

	List<User> getAll();
}
