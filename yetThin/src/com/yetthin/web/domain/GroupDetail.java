package com.yetthin.web.domain;

public class GroupDetail {
	private String groupId;
	private String groupName ;
	private String emotionIndex ;
	private String totleIncome ;
	private String totleIncomeRatio;
	private String evaluateLevel ;
	private String dayIncome ;
	private String monthIncome ;
	private String netIncome ;
	private String userImg;
	private String userName ;
	private String userId ;
	private String vipFlag ;
	private String belongDepart ;
	private String near3MonthIncome ;
	private String latestChangeShareTime ;
	private String createGroupTime;
	private String ifOpen;
	private String myAgreeNum;
	private String isMyAgree;
	private String isMyCollection;
	
	public String getIsMyCollection() {
		return isMyCollection;
	}
	public void setIsMyCollection(String isMyCollection) {
		this.isMyCollection = isMyCollection;
	}
	public String getIsMyAgree() {
		return isMyAgree;
	}
	public void setIsMyAgree(String isMyAgree) {
		this.isMyAgree = isMyAgree;
	}
	public String getMyAgreeNum() {
		return myAgreeNum;
	}
	public void setMyAgreeNum(String myAgreeNum) {
		this.myAgreeNum = myAgreeNum;
	}
	public String getCreateGroupTime() {
		return createGroupTime;
	}
	public void setCreateGroupTime(String createGroupTime) {
		this.createGroupTime = createGroupTime;
	}
	public String getTotleIncomeRatio() {
		return totleIncomeRatio;
	}
	public void setTotleIncomeRatio(String totleIncomeRatio) {
		this.totleIncomeRatio = totleIncomeRatio;
	}
	public String getIfOpen() {
		return ifOpen;
	}
	public void setIfOpen(String ifOpen) {
		this.ifOpen = ifOpen;
	}

	// 选择标的的是否显示列表
	private String stockLableList;
	 
	public String getStockLableList() {
		return stockLableList;
	}
	public void setStockLableList(String stockLableList) {
		this.stockLableList = stockLableList;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getEmotionIndex() {
		return emotionIndex;
	}
	public void setEmotionIndex(String emotionIndex) {
		this.emotionIndex = emotionIndex;
	}
	public String getTotleIncome() {
		return totleIncome;
	}
	public void setTotleIncome(String totleIncome) {
		this.totleIncome = totleIncome;
	}
	public String getEvaluateLevel() {
		return evaluateLevel;
	}
	public void setEvaluateLevel(String evaluateLevel) {
		this.evaluateLevel = evaluateLevel;
	}
	public String getDayIncome() {
		return dayIncome;
	}
	public void setDayIncome(String dayIncome) {
		this.dayIncome = dayIncome;
	}
	public String getMonthIncome() {
		return monthIncome;
	}
	public void setMonthIncome(String monthIncome) {
		this.monthIncome = monthIncome;
	}
	public String getNetIncome() {
		return netIncome;
	}
	public void setNetIncome(String netIncome) {
		this.netIncome = netIncome;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getVipFlag() {
		return vipFlag;
	}
	public void setVipFlag(String vipFlag) {
		this.vipFlag = vipFlag;
	}
	public String getBelongDepart() {
		return belongDepart;
	}
	public void setBelongDepart(String belongDepart) {
		this.belongDepart = belongDepart;
	}
	public String getNear3MonthIncome() {
		return near3MonthIncome;
	}
	public void setNear3MonthIncome(String near3MonthIncome) {
		this.near3MonthIncome = near3MonthIncome;
	}
	public String getLatestChangeShareTime() {
		return latestChangeShareTime;
	}
	public void setLatestChangeShareTime(String latestChangeShareTime) {
		this.latestChangeShareTime = latestChangeShareTime;
	}
	public void setGroup(Group group ){
		this.groupId=group.getGroupId();
		this.groupName=group.getGroupName();
		this.emotionIndex=group.getGroupEmotionIndex()+"";
		this.totleIncome=group.getGroupIncomeTotle()+"";
		this.evaluateLevel=group.getGroupEvaluateLevel();
		this.ifOpen= group.getGroupOpen();
		this.createGroupTime=group.getGroupCreateTime();
	}
	 
	public void setUser(User user){
		this.userName=user.getUserName();
		this.userId=user.getUserId();
		this.vipFlag=user.getUserVipFlag();
		this.belongDepart=user.getUserFirm();
	}

}
