package com.cteam.app.command;

import org.springframework.ui.Model;

import com.cteam.app.dao.CDao;


public class petPhotoUpdateMultiCommand implements AnCommand {

	@Override
	public void execute(Model model) {
		String petPhoto_no = (String)model.asMap().get("petPhoto_no");
		String petPhoto_content = (String)model.asMap().get("petPhoto_content");
		String dbImgPath= (String)model.asMap().get("dbImgPath");
		
		CDao cdao = new CDao();
		
		int state=cdao.petPhotoUpdateMulti(petPhoto_no, petPhoto_content, dbImgPath);
		
	}

}
