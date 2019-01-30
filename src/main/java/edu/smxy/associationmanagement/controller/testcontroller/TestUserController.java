package edu.smxy.associationmanagement.controller.testcontroller;

import org.springframework.boot.autoconfigure.*;
import edu.smxy.associationmanagement.services.users.*;
import org.springframework.beans.factory.annotation.*;
import edu.smxy.associationmanagement.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping({ "/user" })
public class TestUserController
{
    @Autowired
    private UserService userService;
    
    @ResponseBody
    @PostMapping({ "/adduser" })
    public int addUser(final User user) {
        return this.userService.addUser(user);
    }
}
