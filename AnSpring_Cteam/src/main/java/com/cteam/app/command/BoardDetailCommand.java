package com.cteam.app.command;

import org.springframework.ui.Model;

import com.cteam.app.dao.BoardDAO;
import com.cteam.app.dao.CDao;

public class BoardDetailCommand implements AnCommand{
	
	@Override
	public void execute(Model model) {
		
		
		BoardDAO boarddao = new BoardDAO();
		//boarddao.
		//int state = BoardDAO.boarddetail(board_num);
		
		
	}
}
