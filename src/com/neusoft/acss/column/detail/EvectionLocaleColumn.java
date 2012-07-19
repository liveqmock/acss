package com.neusoft.acss.column.detail;

import com.neusoft.acss.bean.Info;
import com.neusoft.acss.column.detail.impl.ColumnDetailImpl;

public class EvectionLocaleColumn implements ColumnDetailImpl {

	private String name = "本地出差地点";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private final int order = 15;

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
		//		RecordBean rb = info.getRecordBean();
		//		EvectionBean evb = info.getEvectionBean();

		return "本地出差";
	}

}