package com.ziya05.scaleadmin.beans;

public enum ChartType {
	NOCHART(0), STANDARD(1), CUSTOM(2);
	
	private int nCode;
	
	private ChartType(int nCode) {
		this.nCode = nCode;
	}
	
	public int value() {
		return this.nCode;
	}
	
	public String toString() {
		return String.valueOf(this.nCode);
	}
}
