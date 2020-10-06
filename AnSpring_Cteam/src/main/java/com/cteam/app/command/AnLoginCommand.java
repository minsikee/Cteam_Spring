package com.cteam.app.command;
import org.springframework.ui.Model;
import com.cteam.app.dao.CDao;
import com.cteam.app.dto.MemberDTO1;

public class AnLoginCommand implements AnCommand{

	@Override
	public void execute(Model model) {		
		String member_id = (String)model.asMap().get("member_id");
		String member_pw = (String)model.asMap().get("member_pw");
		
		//받으면 찍어본다
		System.out.println(member_id);
		System.out.println(member_pw);
	
		CDao cdao = new CDao();
		MemberDTO1 cdto = cdao.anLogin(member_id, member_pw);
		
		model.addAttribute("cLogin", cdto); 
		
	}
	
}
