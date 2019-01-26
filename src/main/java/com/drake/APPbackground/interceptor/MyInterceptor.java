package com.drake.APPbackground.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.drake.APPbackground.entity.UserEntity;
import com.drake.APPbackground.service.RedisService;

@Component
public class MyInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	@Qualifier("RedisServiceImpl")
	private RedisService redisService;

	public RedisService getRedisService() {
		return redisService;
	}

	public void setRedisService(RedisService redisService) {
		this.redisService = redisService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Cookie[] cookies = request.getCookies();
		System.out.println("cookies:" + cookies);
		if (cookies == null) 
		{
			System.out.println("ERROR: Cookies are null or No Cookies cache in service!!!");
			response.sendRedirect("http://192.168.31.253:8080/login");
			return false;
		}
		for (Cookie cookie : cookies) {
			System.out.println("cookie: " + cookie.getName());
			String key = cookie.getName();
			if (key.equals("token")) {
				HttpSession session = request.getSession();
				if (session.getAttribute("user") != null) {
					System.out.println("验证成功");
					return true;
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
						return true;
					}
				}
			}
		}
		// 验证不成功，返回登陆界面
		response.sendRedirect("http://192.168.31.253:8080/login");
		return false;

		// System.out.println("---------------preHandler");
		// Object obj = request.getSession().getAttribute("token");
		// System.out.println("object: " + obj);
		// if (obj == null || !(obj instanceof String))
		// {
		// // System.out.println("http://192.168.31.253:8080/login");
		// response.sendRedirect("http://192.168.31.253:8080/login");
		// return false;
		// }
		// //
		// response.sendRedirect("http://192.168.31.253:8080/source/success");
		// return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("-------------------postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("-------------------afterCompletion");
	}

}
