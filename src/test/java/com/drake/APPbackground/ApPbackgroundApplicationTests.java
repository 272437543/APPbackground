package com.drake.APPbackground;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.drake.APPbackground.entity.SlideEntity;
import com.drake.APPbackground.entity.UserEntity;
import com.drake.APPbackground.mapper.SlideMapper;
import com.drake.APPbackground.mapper.UserMapper;
import com.drake.APPbackground.service.RedisService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApPbackgroundApplicationTests {

	@Autowired
	private UserMapper userMapper;
	@Test
	@Transactional
	public void contextLoads() throws Exception{
		System.out.println("Start testing");
		
		List<UserEntity> all = userMapper.getAll();
		System.out.println(all);
		
		UserEntity user = userMapper.getUserByName("drake");
		System.out.println(user);
	}
	
	@Autowired@Qualifier("RedisServiceImpl")
	private RedisService redisService;
	
	@Test
	public void jedisTest()
	{
		UserEntity user = new UserEntity();
		user.setId(1);
		user.setUsername("admin");
		
		String token = redisService.saveUsers(user);
		System.out.println(token);
	}
	
	@Test
	public void redisDeleteUserByName()
	{
		String username = "abc";
		System.out.println(redisService.deleteUserByName(username));
		
	}
	

}

