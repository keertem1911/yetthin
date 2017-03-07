package com.yetthin.web.domain;

public class StockWithPriceAndRadio {
	private String stockName;
	private String stockCode;
	private String lastPrice;
	private String priceRate;
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getLastPrice() {
		return lastPrice;
	}
	public void setLastPrice(String lastPrice) {
		this.lastPrice = lastPrice;
	}
	public String getPriceRate() {
		return priceRate;
	}
	public void setPriceRate(String priceRate) {
		this.priceRate = priceRate;
	}
	@Override
	public String toString() {
		return "StockWithPriceAndRadio [stockName=" + stockName + ", stockCode=" + stockCode + ", lastPrice="
				+ lastPrice + ", priceRate=" + priceRate + "]";
	}
	
}
