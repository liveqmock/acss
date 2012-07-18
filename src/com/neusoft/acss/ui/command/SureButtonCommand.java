package com.neusoft.acss.ui.command;

import org.apache.commons.lang3.StringUtils;

import com.neusoft.acss.consts.Consts;
import com.neusoft.acss.exception.BizException;
import com.neusoft.acss.ui.UIPanel;
import com.neusoft.acss.ui.command.impl.ButtonCommandImpl;
import com.neusoft.acss.util.PropUtil;

public class SureButtonCommand implements ButtonCommandImpl {

	private UIPanel ui = null;

	public SureButtonCommand(UIPanel ui) {
		this.ui = ui;
	}

	@Override
	public UIPanel handleRequest() throws BizException, Exception {

		if (!ui.getMorningTimeField().getText().matches(Consts.REGEX_TIME)) {
			throw new BizException("上班时间格式不正确，请检查。格式为：HH:mm:ss，如：08:30:00");
		} else if (!ui.getEveningTimeField().getText().matches(Consts.REGEX_TIME)) {
			throw new BizException("下班时间格式不正确，请检查。格式为：HH:mm:ss，如：17:30:00");
		} else if (!StringUtils.isNumeric(ui.getGraceTimeField().getText())) {
			throw new BizException("上下班宽限时间不为整数字，请检查！");
		} else if (!ui.getNoonBeginTimeField().getText().matches(Consts.REGEX_TIME)) {
			throw new BizException("午休开始时间格式不正确，请检查。格式为：HH:mm:ss，如：12:00:00");
		} else if (!ui.getNoonEndTimeField().getText().matches(Consts.REGEX_TIME)) {
			throw new BizException("午休结束时间格式不正确，请检查。格式为：HH:mm:ss，如：13:00:00");
		} else if (!ui.getNoonMiddleTimeField().getText().matches(Consts.REGEX_TIME)) {
			throw new BizException("午休分割时间格式不正确，请检查。格式为：HH:mm:ss，如：12:30:00");
		} else if (!StringUtils.isNumeric(ui.getNoongraceTimeField().getText())) {
			throw new BizException("午休宽限时间不为整数字，请检查！");
		} else {
			PropUtil.writeProperties("work.morning.time", ui.getMorningTimeField().getText(), "");
			PropUtil.writeProperties("work.evening.time", ui.getEveningTimeField().getText(), "");
			PropUtil.writeProperties("work.grace.time", ui.getGraceTimeField().getText(), "");
			PropUtil.writeProperties("work.noon.time.begin", ui.getNoonBeginTimeField().getText(), "");
			PropUtil.writeProperties("work.noon.time.end", ui.getNoonEndTimeField().getText(), "");
			PropUtil.writeProperties("work.noon.time.middle", ui.getNoonMiddleTimeField().getText(), "");
			PropUtil.writeProperties("work.noon.grace.time", ui.getNoongraceTimeField().getText(), "");
			ui.setMessage("设置成功！");
		}
		return ui;
	}

}
