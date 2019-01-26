package com.drake.APPbackground.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.drake.APPbackground.entity.UserEntity;
import com.drake.APPbackground.mapper.CommentMapper;
import com.drake.APPbackground.mapper.SlideMapper;
import com.drake.APPbackground.mapper.UserMapper;
import com.drake.APPbackground.service.RedisService;

@Controller("FatherController")
public class FatherController {
	
	@Value("${ip_address}")
	protected String ip;

	@Value("${server.port}")
	protected String port;

	@Autowired
	@Qualifier("RedisServiceImpl")
	protected RedisService redisService;

	@Autowired
	protected UserMapper userMapper;

	@Autowired
	protected CommentMapper commentMapper;
	
	@Autowired
	protected SlideMapper slideMapper;
	
	protected UserEntity getLoginUser(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			System.out
					.println("ERROR: Cookies are null or No Cookies cache in service!!!");
			return null;
		}
		for (Cookie cookie : cookies) {
			System.out.println("cookie: " + cookie.getName());
			String key = cookie.getName();
			if (key.equals("token")) {
				HttpSession session = request.getSession();
				if (session.getAttribute("user") != null) {
					System.out.println("验证成功");
					return (UserEntity) session.getAttribute("user");
				} else {
					String value = cookie.getValue();
					System.out.println("Value: " + value);
					System.out.println("redisService: " + redisService);
					UserEntity user = redisService.findUserByToKen(value);
					if (user == null)
						break;
					else {
						session.setAttribute("user", user);
						System.out.println("验证成功");
						return user;
					}
				}
			}
		}
		return null;
	}
}
