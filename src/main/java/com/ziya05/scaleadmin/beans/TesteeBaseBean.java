package com.ziya05.scaleadmin.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TesteeBaseBean implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private int id;
	private int scaleId;
	private String scaleName;
	private String userName;
	private String gender;
	private double age;
	private Date testTime;
	private String formatTime;
	
	private TesteePersonalInfoBean personalInfo;
	private List<TesteeDataItemBean> items;
	private String groups;
	
	public TesteeBaseBean() {
		super();
	}

	public TesteeBaseBean(int id, int scaleId, String scaleName, String userName, String gender, double age, Date testTime) {
		super();
		this.id = id;
		this.scaleId = scaleId;
		this.scaleName = scaleName;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.setTestTime(testTime);;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScaleId() {
		return scaleId;
	}

	public void setScaleId(int scaleId) {
		this.scaleId = scaleId;
	}

	public String getScaleName() {
		return scaleName;
	}

	public void setScaleName(String scaleName) {
		this.scaleName = scaleName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	public Date getTestTime() {		
		return testTime;
	}

	public void setTestTime(Date testTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.formatTime = sdf.format(testTime);		
		this.testTime = testTime;
	}
	
	public String getFormatTime() {
		return this.formatTime;
	}

	public TesteePersonalInfoBean getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(TesteePersonalInfoBean personalInfo) {
		this.personalInfo = personalInfo;
	}

	public List<TesteeDataItemBean> getItems() {
		return items;
	}

	public void setItems(List<TesteeDataItemBean> items) {
		this.items = items;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

}
