package com.yetthin.web.domain;

import java.sql.Timestamp;
import java.util.Date;

public class UserInfo {
    private String userId;

    private String phoneNum;

    private Double myMoney;

    private String email;

    private String password;
    //极光id
    private String jpushId;
    //极光开关
    private int jpushStatus;
    //建议
    private String ideaText;
    // 推送类型
    // 1 自选股 2 股票预警 3 公共信息
    private String jpushType;
 
    private String verifyEmail;
    
     private String registerTime;
     
     private String emailStatus;
     public String getEmailStatus() {
		return emailStatus;
	}
     public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}
     
     
     
	public String getRegisterTime() {
		return registerTime;
	}
	
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getVerifyEmail() {
		return verifyEmail;
	}




	public void setVerifyEmail(String verifyEmail) {
		this.verifyEmail = verifyEmail;
	}




	public UserInfo( ) {
		super();
		this.userId = "";
		this.phoneNum = "";
		this.myMoney = 0.0;
		this.email = "";
		this.password = "";
		this.jpushId = "";
		this.jpushStatus = 1;
		this.ideaText = "";
		this.jpushType = "0,0,0";
		this.verifyEmail="false";
		this.registerTime="";		
		emailStatus="0";
	}




	public String getUserId() {
		return userId;
	}




	public void setUserId(String userId) {
		this.userId = userId;
	}




	public String getPhoneNum() {
		return phoneNum;
	}




	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}




	public Double getMyMoney() {
		return myMoney;
	}




	public void setMyMoney(Double myMoney) {
		this.myMoney = myMoney;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getJpushId() {
		return jpushId;
	}




	public void setJpushId(String jpushId) {
		this.jpushId = jpushId;
	}




	public int getJpushStatus() {
		return jpushStatus;
	}




	public void setJpushStatus(int jpushStatus) {
		this.jpushStatus = jpushStatus;
	}




	public String getIdeaText() {
		return ideaText;
	}




	public void setIdeaText(String ideaText) {
		this.ideaText = ideaText;
	}




	public String getJpushType() {
		return jpushType;
	}




	public void setJpushType(String jpushType) {
		this.jpushType = jpushType;
	}
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", phoneNum=" + phoneNum + ", myMoney=" + myMoney + ", email=" + email
				+ ", password=" + password + ", jpushId=" + jpushId + ", jpushStatus=" + jpushStatus + ", ideaText="
				+ ideaText + ", jpushType=" + jpushType + ", verifyEmail=" + verifyEmail + ", registerTime="
				+ registerTime + "]";
	}

 
	
}