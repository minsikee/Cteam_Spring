package com.cteam.app.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.cteam.app.dao.CDao;
import com.cteam.app.dto.CalDTO;

public class CalSelectCommand implements AnCommand{

	@Override
	public void execute(Model model) {
		String calendar_date = (String) model.asMap().get("calendar_date");
		String petname = (String) model.asMap().get("petname");
		CDao cdao = new CDao();
		ArrayList<CalDTO> caldtos = cdao.calSelect(calendar_date, petname);
		
		model.addAttribute("calSelect", caldtos);
	}

}
