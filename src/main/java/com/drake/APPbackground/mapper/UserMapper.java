package com.drake.APPbackground.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.drake.APPbackground.entity.UserEntity;

public interface UserMapper {

	@Select("SELECT `user`.id,`user`.username,`user`.`password`,`user`.icon,`user`.nickname,`user`.permission FROM `user`")
	List<UserEntity> getAll();

	@Select("SELECT `user`.id,`user`.username,`user`.`password`,`user`.icon,`user`.nickname,`user`.permission FROM `user` WHERE `user`.username = #{name}")
	UserEntity getUserByName(@Param("name") String name);

	@Insert("INSERT INTO user(username, password, icon, nickname, permission) "
			+ "VALUES(#{username}, #{password}, #{icon}, #{nickname}, #{permission})")
	int signin(
			@Param("username") String username, 
			@Param("password") String password,
			@Param("icon") String icon,
			@Param("nickname") String nickname, 
			@Param("permission") String permission
			);

}
