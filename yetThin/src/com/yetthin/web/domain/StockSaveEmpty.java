package com.yetthin.web.domain;

public class StockSaveEmpty {
	private String stockCode;
	private String stockRatio;
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getStockRatio() {
		return stockRatio;
	}
	public void setStockRatio(String stockRatio) {
		this.stockRatio = stockRatio;
	}
	@Override
	public String toString() {
		return "StockSaveEmpty [stockCode=" + stockCode + ", stockRatio=" + stockRatio + "]";
	}
 
	
}
