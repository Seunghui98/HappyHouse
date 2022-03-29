package com.ssafy.happyhouse.model.dto;

public class housedeal {
	private String aptcode;
	private String dealamount;
	private String dealyear;
	private String dealmonth;
	private String dealday;
	private String area;
	private String floor;
	public housedeal(String aptcode, String dealamount, String dealyear, String dealmonth, String dealday, String area,
			String floor) {
		super();
		this.aptcode = aptcode;
		this.dealamount = dealamount;
		this.dealyear = dealyear;
		this.dealmonth = dealmonth;
		this.dealday = dealday;
		this.area = area;
		this.floor = floor;
	}
	public String getAptcode() {
		return aptcode;
	}
	public void setAptcode(String aptcode) {
		this.aptcode = aptcode;
	}
	public String getDealamount() {
		return dealamount;
	}
	public void setDealamount(String dealamount) {
		this.dealamount = dealamount;
	}
	public String getDealyear() {
		return dealyear;
	}
	public void setDealyear(String dealyear) {
		this.dealyear = dealyear;
	}
	public String getDealmonth() {
		return dealmonth;
	}
	public void setDealmonth(String dealmonth) {
		this.dealmonth = dealmonth;
	}
	public String getDealday() {
		return dealday;
	}
	public void setDealday(String dealday) {
		this.dealday = dealday;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	
	

}
