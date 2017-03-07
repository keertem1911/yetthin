package com.yetthin.web.domain;

public class StockInfo {
	private String stockCode;
	private String stockName;
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	@Override
	public String toString() {
		return "StockInfo [stockCode=" + stockCode + ", stockName=" + stockName + "]";
	}
	
}
