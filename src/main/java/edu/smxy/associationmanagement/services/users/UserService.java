package edu.smxy.associationmanagement.services.users;

import edu.smxy.associationmanagement.domain.User;

import java.util.List;

public interface UserService {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User query(User user);

    List<User> getAll();

    int addUser(User user);

    User findUser(User user);

    User selectUserById(int id);

}
