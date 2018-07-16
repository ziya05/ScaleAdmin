package com.ziya05.scaleadmin.beans;

import java.io.Serializable;
import java.text.DecimalFormat;

public class FactorScoreLevelBean implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private transient DecimalFormat df = new DecimalFormat("#####.###");
	
	private int factorId;
	private String name;
	private double score;
	private String formatScore;
	private int level;
	private boolean inResult;

	public int getFactorId() {
		return factorId;
	}

	public void setFactorId(int factorId) {
		this.factorId = factorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public boolean isInResult() {
		return inResult;
	}

	public void setInResult(boolean inResult) {
		this.inResult = inResult;
	}

	
	
}
