package com.cteam.app.command;

import org.springframework.ui.Model;

import com.cteam.app.dao.CDao;

public class CalDeleteCommand implements AnCommand {

	@Override
	public void execute(Model model) {
		String calendar_icon = (String) model.asMap().get("calendar_icon");
		
		CDao cdao = new CDao();
		cdao.calDelete(calendar_icon);
		
	}

}