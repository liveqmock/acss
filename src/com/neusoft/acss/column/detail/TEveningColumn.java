package com.neusoft.acss.column.detail;

import com.neusoft.acss.bean.Info;
import com.neusoft.acss.bean.RecordBean;
import com.neusoft.acss.column.detail.impl.ColumnDetailImpl;

public class TEveningColumn implements ColumnDetailImpl {

	private String name = "下班时间";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private final int order = 11;

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
		// EmployeeBean eb = info.getEmployeeBean();
		RecordBean rb = info.getRecordBean();
		// EvectionBean evb = info.getEvectionBean();

		return rb.getTevening();
	}

}
