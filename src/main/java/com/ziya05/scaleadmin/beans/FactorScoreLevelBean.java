package com.ziya05.scaleadmin.beans;

import java.io.Serializable;

public class FactorScoreLevelBean implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private String name;
	private double score;
	private int level;
	
	public FactorScoreLevelBean() {}

	public FactorScoreLevelBean(String name, double score, int level) {
		super();
		this.name = name;
		this.score = score;
		this.level = level;
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
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
