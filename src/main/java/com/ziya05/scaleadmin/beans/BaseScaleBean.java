package com.ziya05.scaleadmin.beans;

public class BaseScaleBean {
	
	private String scaleNumber;
	
	private String name;
	
	private String description;
	
	private int questionCount;
	
	private int chartType;

	public String getScaleNumber() {
		return scaleNumber;
	}

	public void setScaleNumber(String scaleNumber) {
		this.scaleNumber = scaleNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	public int getChartType() {
		return chartType;
	}

	public void setChartType(int chartType) {
		this.chartType = chartType;
	}
	
	
}
