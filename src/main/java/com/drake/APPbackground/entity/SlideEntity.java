package com.drake.APPbackground.entity;

public class SlideEntity {
	
	private Integer id;
	private String title;
	private String image;
	private String href;
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "SlideEntity [id=" + id + ", title=" + title + ", image="
				+ image + ", href=" + href + "]";
	}

	

}
