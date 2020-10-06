package com.cteam.app.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.cteam.app.dao.SuyeonDAO;
import com.cteam.app.dto.BoardselectDTO;

public class MyPostingSelectCommand implements AnCommand {

	@Override
	public void execute(Model model) {
		SuyeonDAO sdao = new SuyeonDAO();
		ArrayList<BoardselectDTO> myboardselect = sdao.myPostingSelect();

		model.addAttribute("myPostingSelect", myboardselect);	
	}

}
