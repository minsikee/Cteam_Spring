package com.cteam.app.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.cteam.app.dao.SuyeonDAO;
import com.cteam.app.dto.CalDTO;

public class CalcalSelectCommand implements AnCommand{

	@Override
	public void execute(Model model) {
		
		String petname = (String) model.asMap().get("petname");

		SuyeonDAO sdao = new SuyeonDAO();
		ArrayList<CalDTO> caldtos = sdao.calcalSelect(petname);
		
		model.addAttribute("calcalSelect", caldtos);
		
	}

}
