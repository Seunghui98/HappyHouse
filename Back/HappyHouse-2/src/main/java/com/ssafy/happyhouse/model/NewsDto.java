package com.ssafy.happyhouse.model;

public class NewsDto {
	String title;
	String link;
	String description;
	public NewsDto() {
		
	}
	public NewsDto(String title, String link, String description) {
		super();
		this.title = title;
		this.link = link;
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "NewsDto [title=" + title + ", link=" + link + ", description=" + description + "]";
	}
	
}
