package com.cteam.app.command.jin;

import org.springframework.ui.Model;

import com.cteam.app.command.AnCommand;
import com.cteam.app.dao.JinDAO;

public class AnPetDeleteCommand implements AnCommand {

	@Override
	public void execute(Model model) {

		String member_id = (String)model.asMap().get("member_id");
		String petname = (String)model.asMap().get("petname");
		
		JinDAO dao = new JinDAO();
		dao.cPetDelete(member_id, petname);
	}

}
