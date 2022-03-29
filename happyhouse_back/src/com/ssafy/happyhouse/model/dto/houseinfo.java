package com.ssafy.happyhouse.model.dto;

public class houseinfo {
	private String aptcode;
	private String aptname;
	private String sidoname;
	private String gugunname;
	private String dongname;
	private int buildyear;
	private String jibun;
	

	public houseinfo(String aptcode, String aptname, String sidoname, String gugunname, String dongname, int buildyear,
			String jibun) {
		super();
		this.aptcode = aptcode;
		this.aptname = aptname;
		this.sidoname = sidoname;
		this.gugunname = gugunname;
		this.dongname = dongname;
		this.buildyear = buildyear;
		this.jibun = jibun;
	}

	public String getSidoname() {
		return sidoname;
	}

	public void setSidoname(String sidoname) {
		this.sidoname = sidoname;
	}

	public String getGugunname() {
		return gugunname;
	}

	public void setGugunname(String gugunname) {
		this.gugunname = gugunname;
	}

	public String getAptcode() {
		return aptcode;
	}
	public void setAptcode(String aptcode) {
		this.aptcode = aptcode;
	}
	public String getAptname() {
		return aptname;
	}
	public void setAptname(String aptname) {
		this.aptname = aptname;
	}
	public String getDongname() {
		return dongname;
	}
	public void setDongname(String dongname) {
		this.dongname = dongname;
	}
	public int getBuildyear() {
		return buildyear;
	}
	public void setBuildyear(int buildyear) {
		this.buildyear = buildyear;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	
	

}
