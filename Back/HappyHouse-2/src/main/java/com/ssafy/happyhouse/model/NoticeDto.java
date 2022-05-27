package com.ssafy.happyhouse.model;

public class NoticeDto {
	private int no;
	private String id;
	private String createtime;
	private String title;
	private String content;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "NoticeDto [NoticeNo=" + no + ", id=" + id + ", createtime=" + createtime + ", title=" + title
				+ ", content=" + content + "]";
	}
	
	
	
}
