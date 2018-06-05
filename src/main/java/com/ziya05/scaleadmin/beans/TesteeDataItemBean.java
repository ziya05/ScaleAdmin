package com.ziya05.scaleadmin.beans;

import java.io.Serializable;

public class TesteeDataItemBean implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private int questionId;
	private String optionId;
	private int score;
	
	public TesteeDataItemBean() {
		super();
	}

	public TesteeDataItemBean(int questionId, String optionId, int score) {
		super();
		this.questionId = questionId;
		this.optionId = optionId;
		this.score = score;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getOptionId() {
		return optionId;
	}

	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
