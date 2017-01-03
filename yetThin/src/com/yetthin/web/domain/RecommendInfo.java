package com.yetthin.web.domain;

public class RecommendInfo {
	private String discussinfoUserId;
	private String discussinfoContext;
	private String discussinfoHeigherId;
	private String groupId;
	private String createTime;
	
	public String getdiscussinfoUserId() {
		return discussinfoUserId;
	}
	public void setdiscussinfoUserId(String discussinfoUserId) {
		this.discussinfoUserId = discussinfoUserId;
	}
	public String getDiscussinfoContext() {
		return discussinfoContext;
	}
	public void setDiscussinfoContext(String discussinfoContext) {
		this.discussinfoContext = discussinfoContext;
	}
	public String getDiscussinfoHeigherId() {
		return discussinfoHeigherId;
	}
	public void setDiscussinfoHeigherId(String discussinfoHeigherId) {
		this.discussinfoHeigherId = discussinfoHeigherId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "RecommendInfo [discussinfoUserId=" + discussinfoUserId + ", discussinfoContext=" + discussinfoContext
				+ ", discussinfoHeigherId=" + discussinfoHeigherId + ", groupId=" + groupId + ", createTime="
				+ createTime + "]";
	}
	public RecommendInfo(String discussinfoUserId, String discussinfoContext, String discussinfoHeigherId, String groupId,
			String createTime) {
		super();
		this.discussinfoUserId = discussinfoUserId;
		this.discussinfoContext = discussinfoContext;
		this.discussinfoHeigherId = discussinfoHeigherId;
		this.groupId = groupId;
		this.createTime = createTime;
	}
	public RecommendInfo() {
		super();
	}
	
}
