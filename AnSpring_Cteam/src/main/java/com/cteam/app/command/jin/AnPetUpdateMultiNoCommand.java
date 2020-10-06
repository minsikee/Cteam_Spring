package com.cteam.app.command.jin;

import org.springframework.ui.Model;

import com.cteam.app.command.AnCommand;
import com.cteam.app.dao.JinDAO;

public class AnPetUpdateMultiNoCommand implements AnCommand {

	@Override
	public void execute(Model model) {

		String originalName = (String)model.asMap().get("originalName");
		String member_id = (String)model.asMap().get("member_id");
		String petname = (String)model.asMap().get("petname");
		String petage = (String)model.asMap().get("petage");
		String petweight = (String)model.asMap().get("petweight");
		String petgender = (String)model.asMap().get("petgender");
		
		JinDAO dao = new JinDAO();
		dao.cPetUpdateMultiNo(originalName,member_id,petname,petage,petweight,petgender);

	}

}
