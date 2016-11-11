package com.yetthin.web.domain;

public class StockOfGroup {
	private String groupName;
	private String userID;
	private String initMoney;
	private String strategyId;
	private String indexCode;
	private String stock;
	private String ifOpen;
	
	public String getIfOpen() {
		return ifOpen;
	}
	public void setIfOpen(String ifOpen) {
		this.ifOpen = ifOpen;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getInitMoney() {
		return initMoney;
	}
	public void setInitMoney(String initMoney) {
		this.initMoney = initMoney;
	}
	public String getStrategyId() {
		return strategyId;
	}
	public void setStrategyId(String strategyId) {
		this.strategyId = strategyId;
	}
	public String getIndexCode() {
		return indexCode;
	}
	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "StockOfGroup [groupName=" + groupName + ", userID=" + userID + ", initMoney=" + initMoney
				+ ", strategyId=" + strategyId + ", indexCode=" + indexCode + ", stock=" + stock + ", ifOpen=" + ifOpen
				+ "]";
	}
	

	 
}
