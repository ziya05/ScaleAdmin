package com.ziya05.scaleadmin.beans;

import java.io.Serializable;

public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L; 

	private int id;
	private String account;
	private String password;
	private String name;
	private int type;
	
	public UserBean() {
		super();
	}

	public UserBean(int id, String account, String password, String name, int type) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.name = name;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
