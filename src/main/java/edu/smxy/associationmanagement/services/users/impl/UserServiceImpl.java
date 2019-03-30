package edu.smxy.associationmanagement.services.users.impl;

import edu.smxy.associationmanagement.domain.User;
import edu.smxy.associationmanagement.mapper.UserMapper;
import edu.smxy.associationmanagement.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public int addUser(final User user) {
		return this.userMapper.insert(user);
	}

	@Override
	public User findUser(final User user) {
		return this.userMapper.query(user);
	}

	@Override
	public User selectUserById(final int id) {
		return this.userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(final Integer id) {
		return this.userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(final User record) {
		return this.userMapper.insert(record);
	}

	@Override
	public int insertSelective(final User record) {
		return this.userMapper.insertSelective(record);
	}

	@Override
	public User selectByPrimaryKey(final Integer id) {
		return this.userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(final User record) {
		return this.userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(final User record) {
		return this.userMapper.updateByPrimaryKey(record);
	}

	@Override
	public User query(final User user) {
		return this.userMapper.query(user);
	}

	@Override
	public List<User> getAll() {
		return this.userMapper.getAll();
	}
}
