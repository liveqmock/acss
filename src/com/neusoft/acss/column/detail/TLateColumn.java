package com.neusoft.acss.column.detail;

import com.neusoft.acss.bean.Info;
import com.neusoft.acss.column.detail.impl.ColumnDetailImpl;

public class TLateColumn implements ColumnDetailImpl {

	private String name = "迟到时间";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return getName();
	}

	@Override
	public String generateColumn(Info info) {
		// EmployeeBean eb = info.getEmployeeBean();
		// RecordBean rb = info.getRecordBean();
		// EvectionBean evb = info.getEvectionBean();

		return "迟到时间";
	}

}