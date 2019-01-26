package com.drake.APPbackground.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drake.APPbackground.entity.UserEntity;

/**
 * 次要页面监听
 * @author xjn
 *
 */
@Controller("IndexController")
public class IndexController extends FatherController{
	
	@RequestMapping("/fail")
	public String fail() {
		System.out.println("fail 界面");
		return "fail";
	}

	@RequestMapping("/success")
	public String success() {
		System.out.println("Success 界面");
		return "success";
	}
	
	@RequestMapping("/")
	public String mianIndex(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 主页
		System.out.println("Main index");
		UserEntity user = getLoginUser(request, response);
		if (user != null)
		{
			model.addAttribute("user", user);
			System.out.println("User: " + user.getNickname());
		}
		return "main";

	}

	@RequestMapping("/say")
	@ResponseBody
	public String receiveByGET(String fromClient) {
		System.out.println("Client: " + fromClient);
		return "Client: " + fromClient;
	}
	
}
