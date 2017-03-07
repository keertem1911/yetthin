package com.yetthin.web.domain;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StockLable {
	private String stocklableId;
	private String stocklableCode;
	private String stocklableName;
	private String stocklableType;
	private String stocklableMarket;
	private String stocklableTrade;
	private String stocklableStatus;
	
	public String getStocklableId() {
		return stocklableId;
	}
	public void setStocklableId(String stocklableId) {
		this.stocklableId = stocklableId;
	}
	public String getStocklableCode() {
		return stocklableCode;
	}
	public void setStocklableCode(String stocklableCode) {
		this.stocklableCode = stocklableCode;
	}
	public String getStocklableName() {
		return stocklableName;
	}
	public void setStocklableName(String stocklableName) {
		this.stocklableName = stocklableName;
	}
	public String getStocklableType() {
		return stocklableType;
	}
	public void setStocklableType(String stocklableType) {
		this.stocklableType = stocklableType;
	}
	public String getStocklableMarket() {
		return stocklableMarket;
	}
	public void setStocklableMarket(String stocklableMarket) {
		this.stocklableMarket = stocklableMarket;
	}
	public String getStocklableTrade() {
		return stocklableTrade;
	}
	public void setStocklableTrade(String stocklableTrade) {
		this.stocklableTrade = stocklableTrade;
	}
	public String getStocklableStatus() {
		return stocklableStatus;
	}
	public void setStocklableStatus(String stocklableStatus) {
		this.stocklableStatus = stocklableStatus;
	}
	@Override
	public String toString() {
		return "StockLable [stocklableId=" + stocklableId + ", stocklableCode=" + stocklableCode + ", stocklableName="
				+ stocklableName + ", stocklableType=" + stocklableType + ", stocklableMarket=" + stocklableMarket
				+ ", stocklableTrade=" + stocklableTrade + ", stocklableStatus=" + stocklableStatus + "]";
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		String stock = stocklableCode.split("[.]")[0];
		String [] arays = stock.split("");
		for (int i =0;i<arays.length;++i) {
			char ch=arays[i].toLowerCase().toCharArray()[0];
			if(ch<'0'||ch>'9'){
				arays[i]=Integer.toString((ch+1)); 
			}
		}
		stock = Stream.of(arays).collect(Collectors.joining());
		 
		return Integer.parseInt(stock);
	}
	 
	
}
