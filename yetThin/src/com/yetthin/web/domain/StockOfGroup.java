package com.yetthin.web.domain;

public class StockOfGroup {
	private String groupName;//组合名称
	private String userID;//用户Id
	private String initMoney;// 初始资金
	private String strategyId;// 策略id  0-4
	private String indexCode;// 选择类别
	private String stock;//  股票编码
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
