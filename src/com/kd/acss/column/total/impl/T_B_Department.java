package com.kd.acss.column.total.impl;

import java.util.List;
import java.util.Map;

import com.kd.acss.bean.Info;
import com.kd.acss.column.detail.impl.D_B_Department;
import com.kd.acss.column.total.IColumnTotal;

public class T_B_Department implements IColumnTotal {

	private String name = "部门";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private final int order = 1;

	@Override
	public int getOrder() {
		return order;
	}

	@Override
	public String getDescription() {
		return getName();
	}

	@Override
	public String generateColumn(Info info) {
		List<Map<String, String>> list = info.getSubList();
		Map<String, String> m = list.get(0);
		return m.get(D_B_Department.class.getName());
	}

}