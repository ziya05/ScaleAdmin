package com.ziya05.scaleadmin.beans;

import java.io.Serializable;

public class ResultAdviceBean implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private String factorName;
	private double score;
	private int level;
	private String description;
	private String advice;
	
	public ResultAdviceBean() {
		super();
	}

	public ResultAdviceBean(String factorName, double score, int level, String description, String advice) {
		super();
		this.factorName = factorName;
		this.score = score;
		this.level = level;
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

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
