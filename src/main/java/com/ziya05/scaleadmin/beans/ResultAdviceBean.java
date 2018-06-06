package com.ziya05.scaleadmin.beans;

import java.io.Serializable;

public class ResultAdviceBean implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private String factorName;
	private String description;
	private String advice;
	
	public ResultAdviceBean() {
		super();
	}

	public ResultAdviceBean(String factorName, String description, String advice) {
		super();
		this.factorName = factorName;
		this.description = description;
		this.advice = advice;
	}

	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

}
