package com.cteam.app.command;

import org.springframework.ui.Model;

import com.cteam.app.dao.SuyeonDAO;

public class CalDeleteCommand implements AnCommand {

	@Override
	public void execute(Model model) {
		String calendar_id = (String) model.asMap().get("calendar_id");
		
		SuyeonDAO sdao = new SuyeonDAO();
		sdao.calDelete(calendar_id);
		
	}

}