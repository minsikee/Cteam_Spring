package com.cteam.app.command;

import java.util.ArrayList;

import org.springframework.ui.Model;


import com.cteam.app.dao.CDao;
import com.cteam.app.dto.PetSelectDTO;

public class petSelectMultiCommand implements AnCommand{

	@Override
	public void execute(Model model) {			
		CDao cdao = new CDao();
		ArrayList<PetSelectDTO> adtos = cdao.petSelectMulti();
		
		model.addAttribute("petSelectMulti", adtos); 
		
	}
}
