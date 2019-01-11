package edu.smxy.associationmanagement.services.users;

import edu.smxy.associationmanagement.domain.User;

public interface UserService {
    int addUser(User user);

    User findUser(User user);

    User selectUserById(int id);
}
