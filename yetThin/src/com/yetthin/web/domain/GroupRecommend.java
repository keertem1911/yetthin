package com.yetthin.web.domain;

import java.util.List;

public class GroupRecommend {

	private String discussinfoId;

	private String discussinfoSendpersonId;

	private String discussinfoCreateTime;

	private String discussinfoHigherId;

	private String discussinfoGroupId;

	private String discussinfoContent;

	private String discussinfoSendpersonName;
	
	private String discussinfoSendpersonImg;

	private String higherName;
	
	private  List<GroupRecommend> upPersonComments;
	
	public List<GroupRecommend> getUpPersonComments() {
		return upPersonComments;
	}

	public void setUpPersonComments(List<GroupRecommend> upPersonComments) {
		this.upPersonComments = upPersonComments;
	}

	public String getDiscussinfoId() {
		return discussinfoId;
	}

	public void setDiscussinfoId(String discussinfoId) {
		this.discussinfoId = discussinfoId;
	}

	public String getDiscussinfoSendpersonId() {
		return discussinfoSendpersonId;
	}

	public void setDiscussinfoSendpersonId(String discussinfoSendpersonId) {
		this.discussinfoSendpersonId = discussinfoSendpersonId;
	}

	public String getDiscussinfoCreateTime() {
		return discussinfoCreateTime;
	}

	public void setDiscussinfoCreateTime(String discussinfoCreateTime) {
		this.discussinfoCreateTime = discussinfoCreateTime;
	}

	public String getDiscussinfoHigherId() {
		return discussinfoHigherId;
	}

	public void setDiscussinfoHigherId(String discussinfoHigherId) {
		this.discussinfoHigherId = discussinfoHigherId;
	}

	public String getDiscussinfoGroupId() {
		return discussinfoGroupId;
	}

	public void setDiscussinfoGroupId(String discussinfoGroupId) {
		this.discussinfoGroupId = discussinfoGroupId;
	}

	public String getDiscussinfoContent() {
		return discussinfoContent;
	}

	public void setDiscussinfoContent(String discussinfoContent) {
		this.discussinfoContent = discussinfoContent;
	}

	 

	public String getDiscussinfoSendpersonName() {
		return discussinfoSendpersonName;
	}

	public void setDiscussinfoSendpersonName(String discussinfoSendpersonName) {
		this.discussinfoSendpersonName = discussinfoSendpersonName;
	}

	public String getDiscussinfoSendpersonImg() {
		return discussinfoSendpersonImg;
	}

	public void setDiscussinfoSendpersonImg(String discussinfoSendpersonImg) {
		this.discussinfoSendpersonImg = discussinfoSendpersonImg;
	}

	public String getHigherName() {
		return higherName;
	}

	public void setHigherName(String higherName) {
		this.higherName = higherName;
	}

	 

	 
	
}
