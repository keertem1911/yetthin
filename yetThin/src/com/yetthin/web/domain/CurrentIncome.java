package com.yetthin.web.domain;

import java.util.List;

public class CurrentIncome {
	private List<CurrentValue>  currentValue;
	private String currentPage;
	private String totlePageSize;
	private String type;
	
	 
	 
	public List<CurrentValue> getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(List<CurrentValue> currentValue) {
		this.currentValue = currentValue;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public String getTotlePageSize() {
		return totlePageSize;
	}
	public void setTotlePageSize(String totlePageSize) {
		this.totlePageSize = totlePageSize;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	 
	 
	 
	 

}
