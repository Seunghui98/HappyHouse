package com.ssafy.happyhouse.model;

public class HeatDto {
	int no;
	String dongName;
	int cnt;
	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	@Override
	public String toString() {
		return "HeatDto [no=" + no + ", dongName=" + dongName + ", cnt=" + cnt + "]";
	}
	
	
	
}
