package edu.smxy.associationmanagement.controller.TestController;

import edu.smxy.associationmanagement.domain.User;
import edu.smxy.associationmanagement.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class TestUserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/adduser")
    public int addUser(User user) {
        return userService.addUser(user);
    }
}
