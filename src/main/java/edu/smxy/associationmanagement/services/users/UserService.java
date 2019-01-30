package edu.smxy.associationmanagement.services.users;

import edu.smxy.associationmanagement.domain.*;
import java.util.*;

public interface UserService
{
    int deleteByPrimaryKey(final Integer id);
    
    int insert(final User record);
    
    int insertSelective(final User record);
    
    User selectByPrimaryKey(final Integer id);
    
    int updateByPrimaryKeySelective(final User record);
    
    int updateByPrimaryKey(final User record);
    
    User query(final User user);
    
    List<User> getAll();
    
    int addUser(final User user);
    
    User findUser(final User user);
    
    User selectUserById(final int id);
}
