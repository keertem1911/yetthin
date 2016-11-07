package com.yetthin.web.domain;

public class Model2Info {
	 
	private String modelIndex;	
	private String modelName;
	
	public Model2Info( String modelIndex, String modelName) {
	 
		this.modelIndex = modelIndex;
		this.modelName = modelName;
	}
	public Model2Info() {
		// TODO Auto-generated constructor stub
	}
	 
	public String getModelIndex() {
		return modelIndex;
	}
	public void setModelIndex(String modelIndex) {
		this.modelIndex = modelIndex;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	

}
