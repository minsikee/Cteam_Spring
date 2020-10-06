package com.cteam.app.command.jin;

import org.springframework.ui.Model;

import com.cteam.app.command.AnCommand;
import com.cteam.app.dao.CDao;



public class AnPetInsertCommand implements AnCommand {

	@Override
	public void execute(Model model) {

		String member_id= (String)model.asMap().get("id");
		String petname = (String)model.asMap().get("petname");
		String petage = (String)model.asMap().get("petage");
		String petweight = (String) model.asMap().get("petweight");
		String petgender = (String) model.asMap().get("petgender");
		String petimagepath = (String) model.asMap().get("petimagepath");

		
		CDao dao = new CDao();
		int state = dao.cPetInsert(member_id, petname, petage, petweight, petgender,petimagepath);
			
		model.addAttribute("cPetInsert", String.valueOf(state)); 

	}

}


