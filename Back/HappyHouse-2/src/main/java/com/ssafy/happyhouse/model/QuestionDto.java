package com.ssafy.happyhouse.model;

public class QuestionDto {
	private int no;
	private String writedate;
	private String title;
	private String content;
	private String userid;
	private boolean isAnswer;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "QuestionDto [no=" + no + ", writedate=" + writedate + ", title=" + title + ", content=" + content
				+ ", userid=" + userid + ", isAnswer=" + isAnswer + "]";
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isAnswer() {
		return isAnswer;
	}
	public void setAnswer(boolean isAnswer) {
		this.isAnswer = isAnswer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	
}
