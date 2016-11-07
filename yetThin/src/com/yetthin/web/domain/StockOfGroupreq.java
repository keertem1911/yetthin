package com.yetthin.web.domain;

public class StockOfGroupreq {
	private String selectedModels;
	private String strategyType;
	private String strategyID;
	private String investCap;
	private String stockCount;
	private String investTime;
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
	public String getStrategyID() {
		return strategyID;
	}
	public void setStrategyID(String strategyID) {
		this.strategyID = strategyID;
	}
	public String getInvestCap() {
		return investCap;
	}
	public void setInvestCap(String investCap) {
		this.investCap = investCap;
	}
	public String getStockCount() {
		return stockCount;
	}
	public void setStockCount(String stockCount) {
		this.stockCount = stockCount;
	}
	public String getInvestTime() {
		return investTime;
	}
	public void setInvestTime(String investTime) {
		this.investTime = investTime;
	}
	
}
