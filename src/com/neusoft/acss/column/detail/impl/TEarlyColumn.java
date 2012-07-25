package com.neusoft.acss.column.detail.impl;

import java.util.Calendar;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.neusoft.acss.bean.EvectionBean;
import com.neusoft.acss.bean.Info;
import com.neusoft.acss.bean.RecordBean;
import com.neusoft.acss.column.detail.IColumnDetail;
import com.neusoft.acss.util.DateUtil;
import com.neusoft.acss.util.PropUtil;

public class TEarlyColumn implements IColumnDetail {

	private String name = "早退时间（分）";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private final int order = 14;

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

		// 根据宽限时间重新计算上班下班等时间和中午休息打卡时间。
		Map<String, String> m = PropUtil.readProperties();
		EvectionBean evb = info.getEvectionBean();
		RecordBean rb = info.getRecordBean();

		// 如果出差登记表有今天对应的记录，那么说明今天有请假或者出差记录。所以是否有异常情况要根据出差登记表重新计算。
		if (evb != null) {

			// 出差时间、病假、事假、年假，可能有几种，1.全天 2.上午 3.下午 4.具体时间如：8:30~12:30 或 8:30-12:30

			// 有全天的情况，今天肯定不算异常情况。即使无打卡记录
			if (evb.hasType("全天")) {
				return null;
			}

			// 有上午的情况，说明下午是有出差或者请假记录，或者有人出差或请假到晚上下班。因此即使下午无打卡记录，也不能计算早退。
			// 如果没有这样的情况或记录，则说明正常来说考勤记录应该有下班打卡记录的。因此这里就不再继续计算下去
			if (evb.hasType("下午") || evb.endWith(m.get("work.evening.time"))) {
				return null;
			}
		}

		// 说明是休息日，休息日即使迟到也不计算早退，本来就是加班啊;
		if (StringUtils.isNotEmpty(rb.getRest())) {
			return null;
		}

		String tevening = rb.getTevening();
		// 如果有异常，则不计算早退时间
		if (StringUtils.isEmpty(tevening)) {
			return null;
		}
		String evening_grace = DateUtil.minusMinutes(m.get("work.evening.time"), m.get("work.grace.time"));

		int min = DateUtil.minusDate(tevening, evening_grace, Calendar.MINUTE);
		return min > 0 ? min + "" : null;
	}

}