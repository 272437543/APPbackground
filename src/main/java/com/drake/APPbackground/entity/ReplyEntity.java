package com.drake.APPbackground.entity;

public class ReplyEntity {
	private Integer id;
	private Integer comment_id;
	private String username;
	private String icon;
	private String content;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getComment_id() {
		return comment_id;
	}
	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "ReplyEntity [id=" + id + ", comment_id=" + comment_id
				+ ", username=" + username + ", icon=" + icon + ", content="
				+ content + "]";
	}
}
