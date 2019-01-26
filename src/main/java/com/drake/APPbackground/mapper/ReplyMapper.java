package com.drake.APPbackground.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.drake.APPbackground.entity.ReplyEntity;

public interface ReplyMapper {

	@Select("SELECT reply.id, reply.comment_id,reply.username,`user`.icon,reply.content FROM reply INNER JOIN `comment` ON reply.comment_id = `comment`.id INNER JOIN `user` ON reply.username = `user`.username WHERE reply.comment_id = #{comment_id}")
	public List<ReplyEntity> getReplybyId(@Param("comment_id") Integer commentId);
}
