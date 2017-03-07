package com.yetthin.web.domain;

public class User {
	private String userId;

	private String userPhone;

	private String userName;

	private String userPassword;

	private String userFirm;

	private String userVipFlag;

	private String userRegisterTime;

	private String userTradePassword;

	private String userCommuniPassword;

	private Double userIncomeTotle;

	private String userHeadPic;
	
	private String email;
	// 极光id
	private String jpushId;
	// 极光开关
	private int jpushStatus;
	// 建议
	private String ideaText;
	// 推送类型
	// 1 自选股 2 股票预警 3 公共信息
	private String jpushType;

	private String verifyEmail;

	private String emailStatus;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getVerifyEmail() {
		return verifyEmail;
	}

	public void setVerifyEmail(String verifyEmail) {
		this.verifyEmail = verifyEmail;
	}

	public String getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserFirm() {
		return userFirm;
	}

	public void setUserFirm(String userFirm) {
		this.userFirm = userFirm;
	}

	public String getUserVipFlag() {
		return userVipFlag;
	}

	public void setUserVipFlag(String userVipFlag) {
		this.userVipFlag = userVipFlag;
	}

	public String getUserRegisterTime() {
		return userRegisterTime;
	}

	public void setUserRegisterTime(String userRegisterTime) {
		this.userRegisterTime = userRegisterTime;
	}

	public String getUserTradePassword() {
		return userTradePassword;
	}

	public void setUserTradePassword(String userTradePassword) {
		this.userTradePassword = userTradePassword;
	}

	public String getUserCommuniPassword() {
		return userCommuniPassword;
	}

	public void setUserCommuniPassword(String userCommuniPassword) {
		this.userCommuniPassword = userCommuniPassword;
	}

	public Double getUserIncomeTotle() {
		return userIncomeTotle;
	}

	public void setUserIncomeTotle(Double userIncomeTotle) {
		this.userIncomeTotle = userIncomeTotle;
	}

	public String getUserHeadPic() {
		return userHeadPic;
	}

	public void setUserHeadPic(String userHeadPic) {
		this.userHeadPic = userHeadPic;
	}
}