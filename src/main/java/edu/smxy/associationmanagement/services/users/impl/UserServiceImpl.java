package edu.smxy.associationmanagement.services.users.impl;

import edu.smxy.associationmanagement.domain.User;
import edu.smxy.associationmanagement.mapper.UserMapper;
import edu.smxy.associationmanagement.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User findUser(User user) {
        return userMapper.query(user);
    }

    @Override
    public User selectUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
