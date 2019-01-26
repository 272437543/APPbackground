package com.drake.APPbackground;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.drake.APPbackground.entity.SlideEntity;
import com.drake.APPbackground.mapper.SlideMapper;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WebSocketTest {

	@Autowired
	private SlideMapper slideMapper;
	
	@Test
	public void readMainSlide()
	{
		List<SlideEntity> allSlides = slideMapper.getAllSlide();
		System.out.println(allSlides);
	}

}
