package com.drake.APPbackground.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.drake.APPbackground.entity.CommentEntity;

public interface CommentMapper {

	@Select("SELECT `comment`.id,`comment`.username,`user`.icon,`comment`.time,`comment`.place,`comment`.words,`comment`.image FROM `comment` INNER JOIN `user` ON `comment`.username = `user`.username")
	public List<CommentEntity> getAllComment();
	
	@Select("SELECT `comment`.id,`comment`.username,`user`.icon,`comment`.time,`comment`.place,`comment`.words,`comment`.image FROM `comment` INNER JOIN `user` ON `comment`.username = `user`.username WHERE `user`.username = #{name}")
	public List<CommentEntity> getCommentByName(@Param("name") String name);
}
