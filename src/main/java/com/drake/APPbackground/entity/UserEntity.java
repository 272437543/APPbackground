package com.drake.APPbackground.entity;

public class UserEntity {

	private Integer id;
	private String username;
	private String password;
	
	private String icon;
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	private String nickname;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	private String permission;
	public String getUsername() {
		return username;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", username=" + username
				+ ", password=" + password + ", nickname=" + nickname
				+ ", icon=" + icon + ", permission=" + permission + "]";
	}
	

	
}
