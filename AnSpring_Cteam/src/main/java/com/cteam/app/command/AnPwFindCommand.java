package com.cteam.app.command;
import org.springframework.ui.Model;
import com.cteam.app.dao.CDao;
import com.cteam.app.dto.MemberDTO1;

public class AnPwFindCommand implements AnCommand{

	@Override
	public void execute(Model model) {		
		String member_id = (String)model.asMap().get("member_id");
		String member_question= (String)model.asMap().get("member_question");
		String member_answer= (String)model.asMap().get("member_answer");

		CDao cdao = new CDao();
		String pw_return = cdao.cPwFind(member_id, member_question,member_answer);
		
		model.addAttribute("cPwFind",pw_return ); 
		
	}
	
}
