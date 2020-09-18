package com.cteam.app.controller;


import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cteam.app.command.AnCommand;
import com.cteam.app.command.AnModifyCommand;

@Controller
public class JinController {

	AnCommand command;
	
	@RequestMapping(value="/cModify",method= {RequestMethod.GET, RequestMethod.POST})
	public String cModify (HttpServletRequest req, Model model ) {

		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
		
		String member_id = (String) req.getParameter("member_id");
		String member_name = (String) req.getParameter("member_name");
		String member_pw = (String) req.getParameter("member_pw");
		String member_question = (String) req.getParameter("member_question");
		String member_answer = (String) req.getParameter("member_answer");
		String member_phonenum = (String) req.getParameter("member_phonenum");
		
		System.out.println(member_id);
		System.out.println(member_name);
		System.out.println(member_pw);
		System.out.println(member_question);
		System.out.println(member_answer);
		System.out.println(member_phonenum);
		
		model.addAttribute("member_id", member_id);
		model.addAttribute("member_name", member_name);
		model.addAttribute("member_pw", member_pw);
		model.addAttribute("member_question", member_question);
		model.addAttribute("member_answer", member_answer);
		model.addAttribute("member_phonenum", member_phonenum);

		command = new AnModifyCommand();
		command.execute(model);
		
		return "cModify";
	}
}
