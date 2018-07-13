package com.ziya05.scaleadmin.beans;

import java.io.Serializable;
import java.text.DecimalFormat;

public class ResultAdviceBean implements Serializable {
	private static final long serialVersionUID = 1L; 
	private DecimalFormat df = new DecimalFormat("#####.###");
	
	private int factorId;
	private String factorName;
	private double score;
	private String formatScore;
	private int level;
	private String description;
	private String advice;
	
	public int getFactorId() {
		return factorId;
	}

	public void setFactorId(int factorId) {
		this.factorId = factorId;
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
		this.formatScore = df.format(score);
	}

	public String getFormatScore() {
		return formatScore;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
