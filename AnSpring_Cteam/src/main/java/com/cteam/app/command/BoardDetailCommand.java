package com.cteam.app.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.cteam.app.dao.BoardDAO;
import com.cteam.app.dto.BoarddetailDTO;

public class BoardDetailCommand implements AnCommand{
	
	@Override
	public void execute(Model model) {
		int board_num = (int) model.asMap().get("board_num");
		BoardDAO boarddao = new BoardDAO();
		ArrayList<BoarddetailDTO> detaildto = boarddao.boarddetail(board_num);
		
		model.addAttribute("boarddetail", detaildto);
	}
}
