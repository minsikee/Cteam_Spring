package com.cteam.app.command;

import org.springframework.ui.Model;

import com.cteam.app.dao.SuyeonDAO;

public class CalUpdateCommand implements AnCommand {

	@Override
	public void execute(Model model) {

		String calendar_date = (String) model.asMap().get("calendar_date");
		String calendar_icon = (String) model.asMap().get("calendar_icon");
		String calendar_memo = (String) model.asMap().get("calendar_memo");
		String calendar_hour = (String) model.asMap().get("calendar_hour");
		String calendar_minute = (String) model.asMap().get("calendar_minute");
		String calendar_id = (String) model.asMap().get("calendar_id");
		
		SuyeonDAO sdao = new SuyeonDAO();
		sdao.calUpdate(calendar_date, calendar_icon, calendar_memo, calendar_hour,calendar_minute,calendar_id);
		
	}
	
}
