package com.cteam.app.command;
import org.springframework.ui.Model;
import com.cteam.app.dao.CDao;
import com.cteam.app.dto.MemberDTO1;

public class AnFindCommand implements AnCommand{

	@Override
	public void execute(Model model) {		
		String member_name = (String)model.asMap().get("member_name");
		String member_phonenum = (String)model.asMap().get("member_phonenum");
	
		CDao cdao = new CDao();
		String id_return = cdao.cFind(member_name, member_phonenum);
		
		model.addAttribute("cFind",id_return ); 
		
	}
	
}
