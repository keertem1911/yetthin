package com.yetthin.web.domain;

public class Summarize {
	private String groupName;	
	private String groupId;
	private String createTime;	
	private String sumIncome;	
	private String RecommentNum;
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getSumIncome() {
		return sumIncome;
	}
	public void setSumIncome(String sumIncome) {
		this.sumIncome = sumIncome;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRecommentNum() {
		return RecommentNum;
	}
	public void setRecommentNum(String recommentNum) {
		RecommentNum = recommentNum;
	}	
	
}
