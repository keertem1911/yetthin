package com.yetthin.web.domain;

import java.util.List;

public class GroupStockByStratgyLable {
	private List<String> stockMarket;
	 
	private List<String> industry;
	private String status;
	private List<String> markets;
	
	
	public GroupStockByStratgyLable( ) {
		 
		this.stockMarket = null;
		 
		this.industry = null;
		this.status = null;
		this.markets = null;
	}
	 
	public List<String> getStockMarket() {
		return stockMarket;
	}

	public void setStockMarket(List<String> stockMarket) {
		this.stockMarket = stockMarket;
	}

 
	public List<String> getIndustry() {
		return industry;
	}
	public void setIndustry(List<String> industry) {
		this.industry = industry;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getMarkets() {
		return markets;
	}
	public void setMarkets(List<String> markets) {
		this.markets = markets;
	}
	
	
}
