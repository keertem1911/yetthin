package com.yetthin.web.domain;

public class StockOfGroupreq {
	private String selectedModels;
	private String strategyType;
	private int strategyID;
	private int investCap;
	private int stockCount;
	private int investTime;
	public String getSelectedModels() {
		return selectedModels;
	}
	public void setSelectedModels(String selectedModels) {
		this.selectedModels = selectedModels;
	}
	public String getStrategyType() {
		return strategyType;
	}
	public void setStrategyType(String strategyType) {
		this.strategyType = strategyType;
	}
	public int getStrategyID() {
		return strategyID;
	}
	public void setStrategyID(int strategyID) {
		this.strategyID = strategyID;
	}
	public int getInvestCap() {
		return investCap;
	}
	public void setInvestCap(int investCap) {
		this.investCap = investCap;
	}
	public int getStockCount() {
		return stockCount;
	}
	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}
	public int getInvestTime() {
		return investTime;
	}
	public void setInvestTime(int investTime) {
		this.investTime = investTime;
	}
	@Override
	public String toString() {
		return "StockOfGroupreq [selectedModels=" + selectedModels + ", strategyType=" + strategyType + ", strategyID="
				+ strategyID + ", investCap=" + investCap + ", stockCount=" + stockCount + ", investTime=" + investTime
				+ "]";
	}
	
	
}
