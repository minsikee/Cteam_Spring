package com.cteam.app.command;

import org.springframework.ui.Model;

import com.cteam.app.dao.SuyeonDAO;
import com.cteam.app.dto.CalDTO;

public class CalInsertCommand implements AnCommand {

	@Override
	public void execute(Model model) {
		
		String calendar_date = (String) model.asMap().get("calendar_date") ;
		String calendar_icon = (String) model.asMap().get("calendar_icon");
		String calendar_memo = (String) model.asMap().get("calendar_memo");
		String calendar_hour = (String) model.asMap().get("calendar_hour");
		String calendar_minute = (String) model.asMap().get("calendar_minute");
		String petname = (String) model.asMap().get("petname");

		SuyeonDAO sdao = new SuyeonDAO();
		sdao.calInsert(calendar_date, calendar_icon, calendar_memo, calendar_hour,calendar_minute,petname);

		
	}

}
