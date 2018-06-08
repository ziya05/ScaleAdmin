package com.ziya05.scaleadmin.factories;

import javax.naming.NamingException;

import com.ziya05.scaleadmin.bo.IScaleBo;
import com.ziya05.scaleadmin.bo.ScaleBo;

public class ScaleBoFactory {
	public static IScaleBo createScaleBo() throws NamingException {
		return new ScaleBo();
	}
}
