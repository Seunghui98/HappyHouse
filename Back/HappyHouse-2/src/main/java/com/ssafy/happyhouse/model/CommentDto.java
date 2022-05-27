package com.ssafy.happyhouse.model;

public class CommentDto {
	
	private int comment_no;
	private String user_id;
	private String content;
	private String comment_time;
	private int Board_no;
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getComment_time() {
		return comment_time;
	}
	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}
	public int getBoard_no() {
		return Board_no;
	}
	public void setBoard_no(int board_no) {
		Board_no = board_no;
	}
	
	
	@Override
	public String toString() {
		return "CommentDto [comment_no=" + comment_no + ", user_id=" + user_id + ", content=" + content
				+ ", comment_time=" + comment_time + ", Board_no=" + Board_no + "]";
	}
   
	
	
}