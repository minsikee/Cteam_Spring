package com.cteam.app.command;

import org.springframework.ui.Model;

import com.cteam.app.dao.CDao;


public class petPhotoUpdateMultiNoCommand implements AnCommand {

	@Override
	public void execute(Model model) {

	
		
			String petPhoto_no = (String)model.asMap().get("petPhoto_no");
			String petPhoto_content = (String)model.asMap().get("petPhoto_content");
			
			CDao cdao = new CDao();
			
			cdao.petPhotoUpdateMultiNo(petPhoto_no,petPhoto_content);
			
			
		
	}

}
