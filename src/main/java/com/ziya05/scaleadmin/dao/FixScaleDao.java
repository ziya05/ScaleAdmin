package com.ziya05.scaleadmin.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ziya05.scaleadmin.beans.TesteeBaseBean;

public class FixScaleDao implements IScaleDao {

	public int getPageCount(String userName, String date, String scaleName) {
		return 20;
	}
	
	public List<TesteeBaseBean> getTesteeBaseBeanList(String userName, String date, String scaleName, int pageIndex, int pageLength) {
		List<TesteeBaseBean> lst = new ArrayList<TesteeBaseBean>();

		lst.add(new TesteeBaseBean(1, 1, "智商测试量表", "杨小胖", "女", new Date()));
		lst.add(new TesteeBaseBean(2, 1, "智商测试量表", "杨小受", "女", new Date()));
		lst.add(new TesteeBaseBean(3, 2, "会不会长胖测试量表", "冬瓜", "男", new Date()));
		lst.add(new TesteeBaseBean(4, 2, "会不会长胖测试量表", "南瓜", "男", new Date()));
		lst.add(new TesteeBaseBean(5, 2, "会不会长胖测试量表", "西瓜", "男", new Date()));
		lst.add(new TesteeBaseBean(6, 2, "会不会长胖测试量表", "北瓜", "男", new Date()));
		
		return lst;
	}
}
