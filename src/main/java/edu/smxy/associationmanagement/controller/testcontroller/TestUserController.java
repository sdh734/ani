package edu.smxy.associationmanagement.controller.testcontroller;

import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.domain.Test;
import edu.smxy.associationmanagement.domain.User;
import edu.smxy.associationmanagement.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping({"/user"})
public class TestUserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping({"/adduser"})
    public int addUser(final User user) {
        return this.userService.addUser(user);
    }

    @ResponseBody
    @RequestMapping({"/test111"})
    public JSONResult test(@RequestBody Test test) {
        return JSONResult.build(200, "ok", test);
    }
}
