package com.drake.APPbackground.service.impl;

import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.drake.APPbackground.entity.UserEntity;
import com.drake.APPbackground.jedis.JedisClient;
import com.drake.APPbackground.service.RedisService;
import com.drake.APPbackground.utils.JsonUtil;

@Service("RedisServiceImpl")
public class RedisServiceImpl implements RedisService {

	@Autowired
	@Qualifier("JedisClientImpl")
	private JedisClient jedisClient;

	@Override
	public String saveUsers(UserEntity user) {
		// if (jedisClient == null ) jedisClient = new JedisClientImpl();
		String token = UUID.randomUUID().toString();
		user.setPassword(null); // 将密码设为空，不能被访问
		jedisClient.set(token, JsonUtil.objectToJson(user));
		jedisClient.expire(token, 30 * 60);
		return token;
	}

	@Override
	public UserEntity findUserByToKen(String token) {
		// if (jedisClient == null ) jedisClient = new JedisClientImpl();
		String json = jedisClient.get(token);
		if (StringUtils.isNotBlank(json)) {
			jedisClient.expire(token, 30 * 60);
			UserEntity user = JsonUtil.jsonToPoj(json, UserEntity.class);
			return user;
		}
		return null;
	}
	
	@Override
	public boolean deleteUserByToken(String token)
	{
		try{
			jedisClient.del(token);
			return true;
		}catch(Exception e)
		{
			System.out.println("删除用户失败");
			return false;
		}
	}
	

	@Override
	public boolean deleteUserByName(String username){
		// if (jedisClient == null ) jedisClient = new JedisClientImpl();
		// 通过用户名删除用户
		Set<String> list = jedisClient.allKeys();
		boolean del = false;
		for (String key:list)
		{
			// System.out.println(key);
			try{
				UserEntity user = findUserByToKen(key);
				if (user != null)
				{
					// System.out.println(user);
					if (user.getUsername().equals(username))
					{
						jedisClient.del(key);
						// return true;
						del = true;
					}
				}
			}catch(Exception e)
			{
				
			}
		}
		return del;
	}

	@Override
	public boolean existUser(String username) {
		Set<String> list = jedisClient.allKeys();
		for (String key:list)
		{
			// System.out.println(key);
			try{
				UserEntity user = findUserByToKen(key);
				if (user != null)
				{
					// System.out.println(user);
					if (user.getUsername().equals(username))
					{
						return true;
					}
				}
			}catch(Exception e)
			{
				
			}
		}
		return false;
	}

}
