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

		lst.add(new TesteeBaseBean(1, 1, "���̲�������", "��С��", "Ů", new Date()));
		lst.add(new TesteeBaseBean(2, 1, "���̲�������", "��С��", "Ů", new Date()));
		lst.add(new TesteeBaseBean(3, 2, "�᲻�᳤�ֲ�������", "����", "��", new Date()));
		lst.add(new TesteeBaseBean(4, 2, "�᲻�᳤�ֲ�������", "�Ϲ�", "��", new Date()));
		lst.add(new TesteeBaseBean(5, 2, "�᲻�᳤�ֲ�������", "����", "��", new Date()));
		lst.add(new TesteeBaseBean(6, 2, "�᲻�᳤�ֲ�������", "����", "��", new Date()));
		
		return lst;
	}
}
