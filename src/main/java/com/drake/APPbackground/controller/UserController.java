package com.drake.APPbackground.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drake.APPbackground.entity.UserEntity;
import com.drake.APPbackground.utils.CookieUtil;
/**
 * 用户配置控制器
 * @author xjn
 *
 */
@Controller("UserController")
public class UserController extends FatherController {

	@RequestMapping("/signin")
	@ResponseBody
	public String signin(String username, String password,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("signin: " + username + " - " + password);
		try {
			int result = userMapper.signin(username, password, "/icon/"
					+ username + ".jpg", "NickName", "用户");
			System.out.println("Result: " + result);
			return "201";
		} catch (Exception e) {
			// e.printStackTrace();
			return "401";
		}
	}

	// @ResponseBody
	@RequestMapping("/login")
	public String login(String username, String password,
			HttpServletRequest request, HttpServletResponse response) {
		// if (username == null || password == null) return "login";
		System.out.println("login: " + username + " - " + password);
		UserEntity user = userMapper.getUserByName(username);

		if (user != null) {
			boolean exist = redisService.existUser(user.getUsername());
			if (exist) {
				System.out.println("用户存在");
			} else {
				System.out.println("用户不存在");
			}

			if (password.equals(user.getPassword())) {
				// 用户数据保存（30分钟）
				String token = redisService.saveUsers(user);

				CookieUtil.setCookie(request, response, "token", token);
				return "redirect:http://" + super.ip + ":" + super.port
						+ "/source/me";
			}
		}
		return "login";
	}

	@RequestMapping("source/logout")
	// @ResponseBody
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("用户登出");
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			System.out
					.println("ERROR: Cookies are null or No Cookies cache in service!!!");
			// response.sendRedirect(location);
			// response.sendRedirect("http://192.168.31.253:8080/login");
			return "fail";
		}
		for (Cookie cookie : cookies) {
			System.out.println("cookie: " + cookie.getName());
			String key = cookie.getName();
			if (key.equals("token")) {
				HttpSession session = request.getSession();
				try {
					CookieUtil.deleteCookie(request, response, "token");
					session.removeAttribute("user");
					String value = cookie.getValue();
					redisService.deleteUserByName(value);
				} catch (Exception e) {
					System.out.println("logout方法：删除失败");

				}
			}
		}
		return "login";
	}

}
