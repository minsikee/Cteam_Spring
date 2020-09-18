package com.cteam.app.command;



import org.springframework.ui.Model;

import com.cteam.app.dao.CDao;


public class cPetbarInsertCommand implements AnCommand{	
	
	@Override
	public void execute(Model model) {
		

		String date = (String)model.asMap().get("date");
		String memo = (String)model.asMap().get("memo");
		String icon = (String)model.asMap().get("icon");
		String hour = (String)model.asMap().get("hour");
		String minute = (String)model.asMap().get("minute");

		
		CDao cdao = new CDao();
		cdao.cPetbarInsertMulti(date,memo,icon,hour,minute);
			
	}	 

}
