package com.cteam.app.command.jin;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.cteam.app.command.AnCommand;
import com.cteam.app.dao.CDao;
import com.cteam.app.dto.PetDTO;

public class AnPetSelectCommand implements AnCommand {

	@Override
	public void execute(Model model) {
		
		String id = (String)model.asMap().get("member_id");
		
		CDao dao = new CDao();
		ArrayList<PetDTO> dto = dao.cPetSelect(id);
		
		model.addAttribute("cPetSelect",dto);
		
		

	}

}
