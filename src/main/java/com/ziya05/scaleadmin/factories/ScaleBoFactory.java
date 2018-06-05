package com.ziya05.scaleadmin.factories;

import com.ziya05.scaleadmin.bo.IScaleBo;
import com.ziya05.scaleadmin.bo.ScaleBo;

public class ScaleBoFactory {
	public static IScaleBo createScaleBo() {
		return new ScaleBo();
	}
}
