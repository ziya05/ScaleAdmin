package com.ziya05.scaleadmin.beans;

import java.io.Serializable;
import java.util.List;

public class TesteePersonalInfoBean implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private List<InfoItemBean> items;
	
	public TesteePersonalInfoBean() {
		super();
	}

	public TesteePersonalInfoBean(List<InfoItemBean> items) {
		super();
		this.items = items;
	}

	public List<InfoItemBean> getItems() {
		return items;
	}

	public void setItems(List<InfoItemBean> items) {
		this.items = items;
	}

}
