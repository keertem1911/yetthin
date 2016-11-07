package com.yetthin.web.domain;

public class RecommendList {
	private String groupName;
	private String incomeRatio;
	private int incomeType;
	private String userName;
	private String userId;
	private String recommendReason;
	private int vipFlag;
	private String createTime;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	 
	public int getIncomeType() {
		return incomeType;
	}
	public void setIncomeType(int incomeType) {
		this.incomeType = incomeType;
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
	public String getRecommendReason() {
		return recommendReason;
	}
	public void setRecommendReason(String recommendReason) {
		this.recommendReason = recommendReason;
	}
	 
	 
	public String getIncomeRatio() {
		return incomeRatio;
	}
	public void setIncomeRatio(String incomeRatio) {
		this.incomeRatio = incomeRatio;
	}
	public int getVipFlag() {
		return vipFlag;
	}
	public void setVipFlag(int vipFlag) {
		this.vipFlag = vipFlag;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	 
	 
	
}
