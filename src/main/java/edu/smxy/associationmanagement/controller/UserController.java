package edu.smxy.associationmanagement.controller;

import edu.smxy.associationmanagement.domain.Association;
import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.domain.MyCookie;
import edu.smxy.associationmanagement.domain.User;
import edu.smxy.associationmanagement.services.association.AssociationService;
import edu.smxy.associationmanagement.services.users.UserService;
import edu.smxy.associationmanagement.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户Controller
 *
 * @author SDH
 */
@ResponseBody
@RestController
@EnableAutoConfiguration
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private AssociationService associationService;

	/**
	 * 添加用户
	 *
	 * @param user
	 * @return
	 */
	@PostMapping({"/adduser"})
	public int addUser(final User user) {
		return this.userService.addUser(user);
	}

	/**
	 * 根据id 获得用户信息
	 *
	 * @param cookie
	 * @return
	 */
	@PostMapping({"/finduser"})
	public JSONResult findUser(@RequestBody final MyCookie cookie) {
		final User user = new User();
		final int userid = cookie.getUserId();
		if ("".equals(userid + "")) {
			return JSONResult.build(500, "未登录", null);
		}
		user.setId(userid);
		final User result = this.userService.selectUserById(user.getId());
		if (result != null) {
			return JSONResult.build(200, "ok", result);
		}
		return JSONResult.build(500, "无法找到_ID_对应的用户", null);
	}

	/**
	 * 更新用户信息
	 *
	 * @param user
	 * @return
	 */
	@PostMapping({"/updateUser"})
	public JSONResult updateUserInfo(@RequestBody User user) {
		int record = userService.updateByPrimaryKey(user);
		if (record > 0) {
			return JSONResult.build(200, "修改成功", null);
		} else {
			return JSONResult.build(500, "修改失败", null);
		}
	}

	/**
	 * 登陆
	 *
	 * @param account
	 * @param password
	 * @param request
	 * @return
	 */
	@PostMapping({"/login"})
	public JSONResult login(
			final String account, final String password, final HttpServletRequest request) {
		final User user = new User();
		user.setAccount(account);
		user.setPassword(password);
		final User result = this.userService.findUser(user);
		if (result != null) {
			if (result.getAssociationid() == - 1) {
				return JSONResult.build(200, "管理员", new MyCookie(result.getId(), request.getSession().getId()));
			} else {
				Association association = associationService.selectByPrimaryKey(result.getAssociationid());
				if (association == null) {
					return JSONResult.build(400, "当前协会已注销", null);
				} else {
					return JSONResult.build(200, "协会", new MyCookie(result.getId(), request.getSession().getId()));
				}
			}
		} else {
			return JSONResult.build(500, "找不到该用户", null);
		}
	}

	/**
	 * 登出
	 *
	 * @param userid
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping({"/logout"})
	public int logout(
			final String userid, final HttpServletRequest request, final HttpServletResponse response) {
		request.getSession().removeAttribute("userid");
		CookieUtils.writeCookie(response, "userid", "");
		CookieUtils.writeCookie(response, "sessionid", "");
		return 0;
	}

	/**
	 * 安卓平台登陆方法
	 *
	 * @param account
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping("/loginforAndroid")
	public JSONResult loginForAndroid(
			String account, String password, HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		user.setAccount(account);
		user.setPassword(password);
		User result = userService.findUser(user);
		if (result != null) {
			return JSONResult.build(200, "", result);
		}
		return JSONResult.build(500, "找不到该用户", null);
	}
}
