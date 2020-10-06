package com.cteam.app.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.cteam.app.dao.CDao;
import com.cteam.app.dto.CalDTO;

public class CalcalSelectCommand implements AnCommand{

	@Override
	public void execute(Model model) {
		
		String petname = (String) model.asMap().get("petname");

		CDao cdao = new CDao();
		ArrayList<CalDTO> caldtos = cdao.calcalSelect(petname);
		
		model.addAttribute("calcalSelect", caldtos);
		
	}

}
