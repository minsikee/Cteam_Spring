package com.cteam.app.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.cteam.app.dao.SuyeonDAO;
import com.cteam.app.dto.CalDTO;

public class CalSelectCommand implements AnCommand{

	@Override
	public void execute(Model model) {
		String calendar_date = (String) model.asMap().get("calendar_date");
		String petname = (String) model.asMap().get("petname");
		SuyeonDAO sdao = new SuyeonDAO();
		ArrayList<CalDTO> caldtos = sdao.calSelect(calendar_date, petname);
		
		model.addAttribute("calSelect", caldtos);
	}

}
