package com.yetthin.web.domain;

public class HeroIncome {
	private String userId;
	private String userName;
	private int vipFlag;
	private String near3MonthIncome;
	private String userImg;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	private String belongDepart;
	
	public int getVipFlag() {
		return vipFlag;
	}
	public void setVipFlag(int vipFlag) {
		this.vipFlag = vipFlag;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	 
	 
	public String getNear3MonthIncome() {
		return near3MonthIncome;
	}
	public void setNear3MonthIncome(String near3MonthIncome) {
		this.near3MonthIncome = near3MonthIncome;
	}
	 
	 
	public String getBelongDepart() {
		return belongDepart;
	}
	public void setBelongDepart(String belongDepart) {
		this.belongDepart = belongDepart;
	}
 
}
