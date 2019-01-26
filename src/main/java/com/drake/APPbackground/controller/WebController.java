package com.drake.APPbackground.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drake.APPbackground.entity.CommentEntity;
import com.drake.APPbackground.entity.ReplyEntity;
import com.drake.APPbackground.entity.SlideEntity;
import com.drake.APPbackground.entity.UserEntity;
import com.drake.APPbackground.mapper.CommentMapper;
import com.drake.APPbackground.mapper.ReplyMapper;
import com.drake.APPbackground.mapper.SlideMapper;
import com.drake.APPbackground.mapper.UserMapper;
import com.drake.APPbackground.service.RedisService;
import com.drake.APPbackground.utils.CookieUtil;
/**
 * 这个控制器的从source目录开始，受拦截器控制
 * @author Drake
 */
@RequestMapping("/source")
@Controller("WebController")
public class WebController extends FatherController{

	@RequestMapping("/allcomment")
	@ResponseBody
	public List<CommentEntity> getComment() {
		return commentMapper.getAllComment();
	}

	@RequestMapping("/comment")
	@ResponseBody
	public List<CommentEntity> getComment(String username) {
		return commentMapper.getCommentByName(username);
	}

	@Autowired
	private ReplyMapper replyMapper;

	@RequestMapping("/reply")
	@ResponseBody
	public List<ReplyEntity> getReply(Integer commentId) {
		return replyMapper.getReplybyId(commentId);
	}

	@RequestMapping("/me")
	@ResponseBody
	public String showMe(HttpServletRequest request,
			HttpServletResponse response) {
		UserEntity user = getLoginUser(request, response);
		if (user != null)
			return user.toString();
		return "test: None";
	}

	@RequestMapping("/slide")
	@ResponseBody
	public List<SlideEntity> allSlides() {
		List<SlideEntity> ret = slideMapper.getAllSlide();
		for (SlideEntity se : ret) {
			se.setImage("http://" + ip + ":" + port + se.getImage());
		}
		return ret;
	}

}
