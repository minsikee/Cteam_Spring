package com.cteam.app.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cteam.app.command.AnCommand;
import com.cteam.app.command.CalDeleteCommand;
import com.cteam.app.command.CalInsertCommand;
import com.cteam.app.command.CalUpdateCommand;
import com.cteam.app.command.CalcalSelectCommand;

@Controller
public class SuyeonController {
	
	AnCommand command;
	
	//캘린더 아이콘 추가
	@RequestMapping(value="/calInsert", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String calInsert(HttpServletRequest req, Model model){
		System.out.println("calInsert()");	
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 		
		
		String calendar_date = (String) req.getParameter("calendar_date");
		String calendar_icon = (String) req.getParameter("calendar_icon");
		String calendar_memo = (String) req.getParameter("calendar_memo");
		String calendar_hour = (String) req.getParameter("calendar_hour");
		String calendar_minute = (String) req.getParameter("calendar_minute");
		String petname = (String) req.getParameter("petname");

		
		System.out.println(calendar_date);
		System.out.println(calendar_icon);
		System.out.println(calendar_memo);
		System.out.println(calendar_hour);
		System.out.println(calendar_minute);
		
		model.addAttribute("calendar_date", calendar_date);
		model.addAttribute("calendar_icon", calendar_icon);
		model.addAttribute("calendar_memo", calendar_memo);	
		model.addAttribute("calendar_hour", calendar_hour);
		model.addAttribute("calendar_minute", calendar_minute);	
		model.addAttribute("petname", petname);	

		
		command = new CalInsertCommand();
		command.execute(model);
		
		return "calInsert";
	} //calInsert()
	
	//캘린더 아이콘 변경
	@RequestMapping(value="/calUpdate", method = {RequestMethod.GET, RequestMethod.POST}  )
	public void calUpdate(HttpServletRequest req, Model model){
		System.out.println("calUpdate()");	
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 		
		
		String calendar_date = (String) req.getParameter("calendar_date");
		String calendar_icon = (String) req.getParameter("calendar_icon");
		String calendar_memo = (String) req.getParameter("calendar_memo");
		String calendar_hour = (String) req.getParameter("calendar_hour");
		String calendar_minute = (String) req.getParameter("calendar_minute");
		String calendar_id = (String) req.getParameter("calendar_id");

		
		System.out.println(calendar_date);
		System.out.println(calendar_icon);
		System.out.println(calendar_memo);
		
		model.addAttribute("calendar_date", calendar_date);
		model.addAttribute("calendar_icon", calendar_icon);
		model.addAttribute("calendar_memo", calendar_memo);	
		model.addAttribute("calendar_hour", calendar_hour);
		model.addAttribute("calendar_minute", calendar_minute);	
		model.addAttribute("calendar_id", calendar_id);	
		
		command = new CalUpdateCommand();
		command.execute(model);

	} //calUpdate()
	
	//캘린더 아이콘 삭제
	@RequestMapping(value="/calDelete", method = {RequestMethod.GET, RequestMethod.POST})
	public void calDelete(HttpServletRequest req, Model model){
		System.out.println("calDelete()");		
		
		model.addAttribute("calendar_id", req.getParameter("calendar_id"));		
				
		System.out.println((String)req.getParameter("calendar_id"));
		
		command = new CalDeleteCommand();
		command.execute(model);	
		
	}
	
	//캘린더 아이콘 붙이기
	@RequestMapping(value="/calcalSelect", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String calcalSelect(HttpServletRequest req, Model model){
		System.out.println("calcalSelect()");
			
		model.addAttribute("petname", req.getParameter("petname"));	

		command = new CalcalSelectCommand();
		command.execute(model);
			
			return "calcalSelect";
	} //calcalSelect()

}