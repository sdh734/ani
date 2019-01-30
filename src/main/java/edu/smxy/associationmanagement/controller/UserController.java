package edu.smxy.associationmanagement.controller;

import org.springframework.boot.autoconfigure.*;
import edu.smxy.associationmanagement.services.users.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import edu.smxy.associationmanagement.domain.*;
import javax.servlet.http.*;
import edu.smxy.associationmanagement.utils.*;

@ResponseBody
@RestController
@EnableAutoConfiguration
public class UserController
{
    @Autowired
    private UserService userService;
    
    @PostMapping({ "/adduser" })
    public int addUser(final User user) {
        return this.userService.addUser(user);
    }
    
    @PostMapping({ "/finduser" })
    public JSONResult findUser(@RequestBody final MyCookie cookie, final HttpServletRequest request) {
        final User user = new User();
        final int userid = cookie.getUserId();
        if (userid + "" == "") {
            final JSONResult jsonResult = JSONResult.build(500, "\u672a\u767b\u5f55", (Object)null);
            return jsonResult;
        }
        user.setId(userid);
        final User result = this.userService.selectUserById((int)user.getId());
        if (result != null) {
            final JSONResult jsonResult2 = JSONResult.build(200, "ok", (Object)result);
            return jsonResult2;
        }
        final JSONResult jsonResult2 = JSONResult.build(500, "\u65e0\u6cd5\u627e\u5230id\u5bf9\u5e94\u7684\u7528\u6237", (Object)null);
        return jsonResult2;
    }
    
    @PostMapping({ "/login" })
    public JSONResult login(final String account, final String password, final HttpServletRequest request, final HttpServletResponse response) {
        final User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        final User result = this.userService.findUser(user);
        if (result != null) {
            request.getSession().setAttribute("userid", (Object)result.getId());
            return JSONResult.build(200, "", (Object)new MyCookie((int)result.getId(), request.getSession().getId()));
        }
        return JSONResult.build(500, "\u627e\u4e0d\u5230\u8be5\u7528\u6237", (Object)null);
    }
    
    @PostMapping({ "/logout" })
    public int logout(final String userid, final HttpServletRequest request, final HttpServletResponse response) {
        request.getSession().removeAttribute("userid");
        CookieUtils.writeCookie(response, "userid", "");
        CookieUtils.writeCookie(response, "sessionid", "");
        return 0;
    }
}
