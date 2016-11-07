package com.yetthin.web.domain;

import java.util.List;

public class GroupRecommend {
	private String belongGroupId ;
	private String recommendPerson ;
	private String vipFlag ;
	private String commentTime ;
	private List<UpPersonComment> upPersonComments ;
	private String commentContext ;
	public String getBelongGroupId() {
		return belongGroupId;
	}
	public void setBelongGroupId(String belongGroupId) {
		this.belongGroupId = belongGroupId;
	}
	public String getRecommendPerson() {
		return recommendPerson;
	}
	public void setRecommendPerson(String recommendPerson) {
		this.recommendPerson = recommendPerson;
	}
	 
	public String getVipFlag() {
		return vipFlag;
	}
	public void setVipFlag(String vipFlag) {
		this.vipFlag = vipFlag;
	}
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
	 
	public List<UpPersonComment> getUpPersonComments() {
		return upPersonComments;
	}
	public void setUpPersonComments(List<UpPersonComment> upPersonComments) {
		this.upPersonComments = upPersonComments;
	}
	public String getCommentContext() {
		return commentContext;
	}
	public void setCommentContext(String commentContext) {
		this.commentContext = commentContext;
	}
	
}
