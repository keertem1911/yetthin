package com.yetthin.web.domain;

import java.util.List;

public class CurrentIncome {
	private List<CurrentValue> currentValue;

	private String type;

	public List<CurrentValue> getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(List<CurrentValue> currentValue) {
		this.currentValue = currentValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
