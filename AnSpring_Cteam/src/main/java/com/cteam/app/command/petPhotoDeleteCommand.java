package com.cteam.app.command;

import org.springframework.ui.Model;

import com.cteam.app.dao.CDao;

public class petPhotoDeleteCommand implements AnCommand{

	@Override
	public void execute(Model model) {
		String petPhoto_no = (String) model.asMap().get("petPhoto_no");
		
		CDao cdao = new CDao();
		int state =cdao.petPhotoDelete(petPhoto_no);
		System.out.println(state);
	}

}
