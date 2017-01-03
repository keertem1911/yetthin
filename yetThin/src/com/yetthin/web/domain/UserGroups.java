package com.yetthin.web.domain;

public class UserGroups {
	private String groupId;
	private String groupName;	
	private String imcomeRatio; 
	private String createTime; 
	private String sumIncome;	
	private String RecommentNum;
	private String myAgreeNum;
	
	public String getMyAgreeNum() {
		return myAgreeNum;
	}
	public void setMyAgreeNum(String myAgreeNum) {
		this.myAgreeNum = myAgreeNum;
	}
	private long groupInitMoney;
	
	public long getGroupInitMoney() {
		return groupInitMoney;
	}
	public void setGroupInitMoney(long groupInitMoney) {
		this.groupInitMoney = groupInitMoney;
	}
	public String getSumIncome() {
		return sumIncome;
	}
	public void setSumIncome(String sumIncome) {
		this.sumIncome = sumIncome;
	}
	public String getRecommentNum() {
		return RecommentNum;
	}
	public void setRecommentNum(String recommentNum) {
		RecommentNum = recommentNum;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getImcomeRatio() {
		return imcomeRatio;
	}
	public void setImcomeRatio(String imcomeRatio) {
		this.imcomeRatio = imcomeRatio;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	 
	
}
