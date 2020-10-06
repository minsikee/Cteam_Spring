package com.cteam.app.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.cteam.app.dao.CDao;
import com.cteam.app.dto.petPhotoDTO;

public class cPetPhotoSelectCommand implements AnCommand {

	@Override
	public void execute(Model model) {
		
		String member_id = (String)model.asMap().get("member_id");
		String petName=	(String)model.asMap().get("petName");
				
		
		CDao cdao = new CDao();
		
		
		ArrayList<petPhotoDTO> cdtos = cdao.cPhotoSelect(petName,member_id);
		
		System.out.println(cdtos.get(0).getPetName());
		
		model.addAttribute("cPhotoSelect", cdtos); 
		
	}

}
