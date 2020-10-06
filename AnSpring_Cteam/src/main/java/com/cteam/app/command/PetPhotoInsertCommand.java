package com.cteam.app.command;

import org.springframework.ui.Model;

import com.cteam.app.dao.CDao;

public class PetPhotoInsertCommand implements AnCommand {

	@Override
	public void execute(Model model) {

				String member_id= (String)model.asMap().get("member_id");
				String petName = (String)model.asMap().get("petName");
				String PetPhoto_content = (String)model.asMap().get("PetPhoto_content");
				String imageDbPathA = (String) model.asMap().get("imageDbPathA");
				String petgender = (String) model.asMap().get("petgender");
				String petimagepath = (String) model.asMap().get("petimagepath");

				
				CDao dao = new CDao();
				int state = dao.PetPhotoInsert(member_id, petName, PetPhoto_content, imageDbPathA);
					
				model.addAttribute("state", String.valueOf(state)); 

			
		

	}

}
