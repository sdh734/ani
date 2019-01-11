package edu.smxy.associationmanagement.controller;

import com.sun.xml.internal.bind.v2.TODO;
import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.domain.MyCookie;
import edu.smxy.associationmanagement.domain.User;
import edu.smxy.associationmanagement.services.users.UserService;
import edu.smxy.associationmanagement.utils.CookieUtils;
import edu.smxy.associationmanagement.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@ResponseBody
@RestController
@EnableAutoConfiguration
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/adduser")
    public int addUser(User user) {
        return userService.addUser(user);
    }

    @PostMapping("/finduser")
    public JSONResult findUser(@RequestBody MyCookie cookie, HttpServletRequest request) {
        User user = new User();
        int userid = cookie.getUserId();
        if (userid + "" != "") {
            user.setId(userid);
            User result = userService.selectUserById(user.getId());
            if (result != null) {
                //已有用户登录
                JSONResult jsonResult = JSONResult.build(200, "ok", result);
                return jsonResult;
            } else {
                //无法找到id对应的用户
                JSONResult jsonResult = JSONResult.build(500, "无法找到id对应的用户", null);
                return jsonResult;
            }
        } else {
            //
            JSONResult jsonResult = JSONResult.build(500, "未登录", null);
            return jsonResult;
        }
    }


    @PostMapping("/login")
    public JSONResult login(String account, String password, HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        User result = userService.findUser(user);
        if (result != null) {
            request.getSession().setAttribute("userid", result.getId());
            return JSONResult.build(200, "", new MyCookie(result.getId(), request.getSession().getId()));
        }
        return JSONResult.build(500, "找不到该用户", null);
    }


    @PostMapping("/logout")
    public int logout(String userid, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("userid");
        CookieUtils.writeCookie(response, "userid", "");
        CookieUtils.writeCookie(response, "sessionid", "");
        return 0;
    }
}
