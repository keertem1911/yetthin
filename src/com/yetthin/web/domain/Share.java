package com.yetthin.web.domain;

public class Share {
    private String shareId;

    private String shareGroupId;

    private String shareStocklableId;

    private Double shareStartFund;

    private Double shareCurrentNum;

    private Double shareCurrentIncome;
    
    private String shareStocklableName;
    
    private String shareStatus;
    

    public String getShareStatus() {
		return shareStatus;
	}

	public void setShareStatus(String shareStatus) {
		this.shareStatus = shareStatus;
	}

	public String getShareStocklableName() {
		return shareStocklableName;
	}

	public void setShareStocklableName(String shareStocklableName) {
		this.shareStocklableName = shareStocklableName;
	}

	public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getShareGroupId() {
        return shareGroupId;
    }

    public void setShareGroupId(String shareGroupId) {
        this.shareGroupId = shareGroupId;
    }

    public String getShareStocklableId() {
        return shareStocklableId;
    }

    public void setShareStocklableId(String shareStocklableId) {
        this.shareStocklableId = shareStocklableId;
    }

    public Double getShareStartFund() {
        return shareStartFund;
    }

    public void setShareStartFund(Double shareStartFund) {
        this.shareStartFund = shareStartFund;
    }

    
    public Double getShareCurrentNum() {
		return shareCurrentNum;
	}

	public void setShareCurrentNum(Double shareCurrentNum) {
		this.shareCurrentNum = shareCurrentNum;
	}

	public Double getShareCurrentIncome() {
        return shareCurrentIncome;
    }

    public void setShareCurrentIncome(Double shareCurrentIncome) {
        this.shareCurrentIncome = shareCurrentIncome;
    }

	@Override
	public String toString() {
		return "Share [shareId=" + shareId + ", shareGroupId=" + shareGroupId + ", shareStocklableId="
				+ shareStocklableId + ", shareStartFund=" + shareStartFund + ", shareCurrentNum=" + shareCurrentNum
				+ ", shareCurrentIncome=" + shareCurrentIncome + ", shareStocklableName=" + shareStocklableName + "]";
	}

	public void updateShare(Share share,boolean isNew) {
		// TODO Auto-generated method stub
		this.shareCurrentIncome=share.getShareStartFund();
		this.shareCurrentNum=share.getShareCurrentNum();
		this.shareStartFund=share.getShareStartFund();
		this.shareStatus="1";
		if(isNew){
			this.shareGroupId=share.shareGroupId;
			this.shareId=share.getShareId();
		}
	}

	 
    
}