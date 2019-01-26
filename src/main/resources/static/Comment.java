package com.drake.APPbackground.utils;

import java.sql.Date;


public class Comment {
	private Integer id;
	private String username;
	private Date time;
	private String place;
	private String words;
	private String image;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "CommentEntity [id=" + id + ", username=" + username + ", time="
				+ time + ", place=" + place + ", words=" + words + ", image="
				+ image + "]";
	}
	
}
