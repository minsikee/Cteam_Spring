package com.cteam.app.command;
import org.springframework.ui.Model;
import com.cteam.app.dao.CDao;
import com.cteam.app.dto.MemberDTO1;
import com.cteam.app.dao.CDao;

public class AnPwUpdateCommand implements AnCommand{

	@Override
	public void execute(Model model) {		
		String member_id = (String)model.asMap().get("member_id");
		String member_pw = (String)model.asMap().get("member_pw");	
	
		CDao cdao = new CDao();
		int state = cdao.cPwUpdate(member_id, member_pw);
		
		model.addAttribute("cPwUpdate", String.valueOf(state)); 
	}
	
}
