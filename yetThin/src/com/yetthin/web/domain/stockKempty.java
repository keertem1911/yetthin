package com.yetthin.web.domain;

import java.util.Comparator;

public class stockKempty{
	private String stockCode;
	private String stockName;
	private String open;
	private String height;
	private String low;
	private String close;
	private int passRate;
	private int fundRate;
	public int getPassRate() {
		return passRate;
	}

	public void setPassRate(int passRate) {
		this.passRate = passRate;
	}

	public int getFundRate() {
		return fundRate;
	}

	public void setFundRate(int fundRate) {
		this.fundRate = fundRate;
	}

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

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	@Override
	public String toString() {
		return "stockKempty [stockCode=" + stockCode + ", stockName=" + stockName + ", open=" + open + ", height="
				+ height + ", low=" + low + ", close=" + close + ", passRate=" + passRate + ", fundRate=" + fundRate
				+ "]";
	}

}
