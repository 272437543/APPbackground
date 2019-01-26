package com.drake.APPbackground.service;

import com.drake.APPbackground.entity.UserEntity;



public interface RedisService {
	/**
	 * 保存用户
	 * @param user
	 * @return 生存令牌token
	 */
	public String saveUsers(UserEntity user);
	
	/**
	 * 通过token令牌查找用户
	 * @param token
	 * @return
	 */
	public UserEntity findUserByToKen(String token);
	
	/**
	 * 通过用户名删除用户登录状态
	 * @param username
	 * @return 返回是否删除成功
	 */
	public boolean deleteUserByName(String username);
	
	/**
	 * 通过token删除用户
	 * @param token
	 * @return
	 */
	public boolean deleteUserByToken(String token);
	
	/**
	 * 用户是否存在
	 * @param username
	 * @return
	 */
	public boolean existUser(String username);
}
