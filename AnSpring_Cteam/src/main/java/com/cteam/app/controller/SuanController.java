package com.cteam.app.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.cteam.app.command.AnJoinCommand;
import com.cteam.app.command.AnCommand;
import com.cteam.app.command.AnFindCommand;
import com.cteam.app.command.AnLoginCommand;
import com.cteam.app.command.AnPwFindCommand;
import com.cteam.app.command.AnPwUpdateCommand;
import com.cteam.app.command.CalDeleteCommand;
import com.cteam.app.command.CalInsertCommand;
import com.cteam.app.command.CalSelectCommand;
import com.cteam.app.command.CalUpdateCommand;
import com.cteam.app.command.cPetbarInsertCommand;
import com.cteam.app.command.petSelectMultiCommand;

@Controller
public class SuanController {
	
	AnCommand command;
	
	@RequestMapping(value="/cLogin", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anLogin(HttpServletRequest req, Model model){
		System.out.println("cLogin()");
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		
		String member_id = (String) req.getParameter("member_id");
		String member_pw = (String) req.getParameter("member_pw");
		
		//받으면 찍어본다
		System.out.println(member_id);
		System.out.println(member_pw);
		
		model.addAttribute("member_id", member_id);
		model.addAttribute("member_pw", member_pw);
		
		command = new AnLoginCommand();
		command.execute(model);
		
		return "cLogin";
	}
	
	@RequestMapping(value="/cJoin", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anJoin(HttpServletRequest req, Model model){
		System.out.println("cJoin()");
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 		
		
		String member_id = (String) req.getParameter("member_id");
		String member_pw = (String) req.getParameter("member_pw");
		String member_name = (String) req.getParameter("member_name");
		String member_question = (String) req.getParameter("member_question");
		String member_answer = (String) req.getParameter("member_answer");
		String member_phonenum = (String) req.getParameter("member_phonenum");
		
		System.out.println(member_id);
		System.out.println(member_pw);
		System.out.println(member_name);
		System.out.println(member_question);
		System.out.println(member_answer);
		System.out.println(member_phonenum);
		
		model.addAttribute("member_id", member_id);
		model.addAttribute("member_pw", member_pw);
		model.addAttribute("member_name", member_name);
		model.addAttribute("member_question", member_question);
		model.addAttribute("member_answer", member_answer);
		model.addAttribute("member_phonenum", member_phonenum);
		
		command = new AnJoinCommand();
		command.execute(model);
		
		return "cJoin";
	}
	
	@RequestMapping(value="/cFind", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String cFind(HttpServletRequest req, Model model){
		System.out.println("cFind()");
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		
		String member_name = (String) req.getParameter("member_name");
		String member_phonenum = (String) req.getParameter("member_phonenum");
		
		//받으면 찍어본다
		System.out.println(member_name);
		System.out.println(member_phonenum);
		
		model.addAttribute("member_name", member_name);
		model.addAttribute("member_phonenum", member_phonenum);
		
		command = new AnFindCommand();
		command.execute(model);
		
		return "cFind";
	}
	
	@RequestMapping(value="/cPwFind", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String cPwFind(HttpServletRequest req, Model model){
		System.out.println("cPwFind()");
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		
		String member_id = (String) req.getParameter("member_id");
		String member_question = (String) req.getParameter("member_question");
		String member_answer = (String) req.getParameter("member_answer");

		
		//받으면 찍어본다
		System.out.println(member_id);
		System.out.println(member_question);
		System.out.println(member_answer);

		
		model.addAttribute("member_id", member_id);
		model.addAttribute("member_question", member_question);
		model.addAttribute("member_answer", member_answer);

		
		command = new AnPwFindCommand();
		command.execute(model);
		
		return "cPwFind";
	}
	
	@RequestMapping(value="/cPwUpdate", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String cPwUpdate(HttpServletRequest req, Model model){
		System.out.println("cPwUpdate()");
		
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 		
		
		String member_pw = (String) req.getParameter("member_pw");
		String member_id = (String) req.getParameter("member_id");
		
		
		System.out.println("pw:"+member_pw+"id"+member_id);
	
		
		model.addAttribute("member_pw", member_pw);
		model.addAttribute("member_id", member_id);
		
		command = new AnPwUpdateCommand();
		command.execute(model);
		
		return "cPwUpdate";
	}
	@RequestMapping(value="/petSelectMulti", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String petSelectMulti(HttpServletRequest req, Model model){
		System.out.println("anSelectMulti()");
		
		command = new petSelectMultiCommand();
		command.execute(model);
		
		return "cPetSelectMulti";
		
		
	}
	
	@RequestMapping(value="/cPetBarInsert", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anInsertMulti(HttpServletRequest req, Model model){
		System.out.println("cPetBarInsert()");	
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String date = (String) req.getParameter("date");
		String memo = (String) req.getParameter("memo");
		String icon = (String) req.getParameter("icon");
		String hour = (String) req.getParameter("hour");
		String minute = (String) req.getParameter("minute");



		
		System.out.println(date+memo+memo+hour+minute);
		
		model.addAttribute("date", date);
		model.addAttribute("memo", memo);
		model.addAttribute("icon", icon);
		model.addAttribute("hour", hour);
		model.addAttribute("minute", minute);
	
		
			
		command = new cPetbarInsertCommand();
		command.execute(model);
		
		return "cPetbarInsertMulti";
	}
	
	
	
	//캘린더 아이콘 선택
	@RequestMapping(value="/calSelect", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String calSelect(HttpServletRequest req, Model model){
		System.out.println("calSelect()");
		
		model.addAttribute("calendar_date", req.getParameter("calendar_date"));	
		
		command = new CalSelectCommand();
		command.execute(model);
		
		return "calSelect";
	} //calSelect()
	
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
		
		System.out.println(calendar_date);
		System.out.println(calendar_icon);
		System.out.println(calendar_memo);
		
		model.addAttribute("calendar_date", calendar_date);
		model.addAttribute("calendar_icon", calendar_icon);
		model.addAttribute("calendar_memo", calendar_memo);	
		model.addAttribute("calendar_hour", calendar_hour);
		model.addAttribute("calendar_minute", calendar_minute);	
		
		command = new CalUpdateCommand();
		command.execute(model);

	} //calUpdate()
	
	//캘린더 아이콘 삭제
	@RequestMapping(value="/calDelete", method = {RequestMethod.GET, RequestMethod.POST})
	public void calDelete(HttpServletRequest req, Model model){
		System.out.println("calDelete()");		
		
		model.addAttribute("calendar_icon", req.getParameter("calendar_icon"));		
				
		System.out.println((String)req.getParameter("calendar_icon"));
		
		command = new CalDeleteCommand();
		command.execute(model);	
		
	}



}
