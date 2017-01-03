package com.yetthin.web.domain;

import java.util.List;

public class DisscussInfo {
    private String discussinfoId;

    private String discussinfoSendpersonId;

    private String discussinfoCreateTime;

    private String discussinfoHigherId;

    private String discussinfoGroupId;

    private String discussinfoContent;
    
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
}