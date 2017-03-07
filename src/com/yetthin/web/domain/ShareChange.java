package com.yetthin.web.domain;


public class ShareChange {
    private String sharechangeId;

    private String sharechangeStocklableId;

    private String sharechangeTime;

    private Double sharechangePrice;

    private String sharechangeDir;

    private Double sharechangeNum;
    
    private String sharechangeGroupId;
    
 
    
    
    public String getSharechangeGroupId() {
		return sharechangeGroupId;
	}

	public void setSharechangeGroupId(String sharechangeGroupId) {
		this.sharechangeGroupId = sharechangeGroupId;
	}

	public String getSharechangeId() {
        return sharechangeId;
    }

    public void setSharechangeId(String sharechangeId) {
        this.sharechangeId = sharechangeId;
    }

    

    public String getSharechangeStocklableId() {
		return sharechangeStocklableId;
	}

	public void setSharechangeStocklableId(String sharechangeStocklableId) {
		this.sharechangeStocklableId = sharechangeStocklableId;
	}

	public String getSharechangeTime() {
        return sharechangeTime;
    }

    public void setSharechangeTime(String sharechangeTime) {
        this.sharechangeTime = sharechangeTime;
    }

    public Double getSharechangePrice() {
        return sharechangePrice;
    }

    public void setSharechangePrice(Double sharechangePrice) {
        this.sharechangePrice = sharechangePrice;
    }

    public String getSharechangeDir() {
        return sharechangeDir;
    }

    public void setSharechangeDir(String sharechangeDir) {
        this.sharechangeDir = sharechangeDir;
    }

	public Double getSharechangeNum() {
		return sharechangeNum;
	}

	public void setSharechangeNum(Double sharechangeNum) {
		this.sharechangeNum = sharechangeNum;
	}

    
}