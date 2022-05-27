package com.ssafy.happyhouse.model;

public class LikeDto {
	private int no;
	private String user_id;
	private int apt_code;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUser_id() {
		return user_id;
	}
	public int getApt_code() {
		return apt_code;
	}
	public void setApt_code(int apt_code) {
		this.apt_code = apt_code;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "LikeDto [no=" + no + ", user_id=" + user_id + ", apt_code=" + apt_code + "]";
	}
	
	
	
}
