package com.ziya05.scaleadmin.beans;

import java.io.Serializable;

public class TesteeDataItemBean implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private int questionId;
	private String optionId;
	private int score;
	private String text;
	
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
