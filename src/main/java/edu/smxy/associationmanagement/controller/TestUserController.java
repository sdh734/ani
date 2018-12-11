package edu.smxy.associationmanagement.controller;

import edu.smxy.associationmanagement.domain.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/testboot")
public class TestUserController {

    @RequestMapping("getuser")
    public User getUser(){
        User user=new User();
        user.setName("sdh");
        return user;
    }
}
