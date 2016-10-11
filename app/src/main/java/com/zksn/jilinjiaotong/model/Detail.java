package com.zksn.jilinjiaotong.model;

public class Detail {
	private String date; //ʱ��
	private String pt;//����ʱ��
	private Day day;//��������
	private Day night;//��������
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPt() {
		return pt;
	}
	public void setPt(String pt) {
		this.pt = pt;
	}
	public Day getDay() {
		return day;
	}
	public void setDay(Day day) {
		this.day = day;
	}
	public Day getNight() {
		return night;
	}
	public void setNight(Day night) {
		this.night = night;
	}

}
