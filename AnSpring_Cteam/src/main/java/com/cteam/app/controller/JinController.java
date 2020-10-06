package com.cteam.app.controller;


import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.cteam.app.command.AnCommand;
import com.cteam.app.command.jin.AnModifyCommand;
import com.cteam.app.command.jin.AnPetDeleteCommand;
import com.cteam.app.command.jin.AnPetInsertCommand;
import com.cteam.app.command.jin.AnPetSelectCommand;
import com.cteam.app.command.jin.AnPetUpateCommand;
import com.cteam.app.command.jin.AnPetUpdateMultiNoCommand;


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
	
	
	
	//디렉토리가 존재하지 않을 때(이미지첨부시사용)
	public void makeDir(HttpServletRequest req){
		File f = new File(req.getSession().getServletContext()
				.getRealPath("/resources"));
		if(!f.isDirectory()){
		f.mkdir();
		}	
	}
	//마이펫 등록
	@RequestMapping(value="/cPetInsert", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String cPetInsert(HttpServletRequest req, Model model){
		System.out.println("cPetInsert()");	
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		
		String id = (String) req.getParameter("id");
		String petname = (String) req.getParameter("petname");
		String petage = (String) req.getParameter("petage");
		String petweight = (String) req.getParameter("petweight");
		String petgender = (String) req.getParameter("petgender");
		String petimagepath = (String) req.getParameter("petimagepath");
	
		
		System.out.println(id);
		System.out.println(petname);
		System.out.println(petage);
		System.out.println(petweight);
		System.out.println(petgender);
		System.out.println(petimagepath);

		model.addAttribute("id", id);
		model.addAttribute("petname", petname);
		model.addAttribute("petage", petage);	
		model.addAttribute("petweight", petweight);	
		model.addAttribute("petgender", petgender);	
		model.addAttribute("petimagepath", petimagepath);	

		
		MultipartRequest multi = (MultipartRequest)req;
		MultipartFile file = multi.getFile("image");
		
			
		if(file != null) {
			String fileName = file.getOriginalFilename();
			System.out.println(fileName);
			
			// 디렉토리 존재하지 않으면 생성
			makeDir(req);	
				
			if(file.getSize() > 0){			
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/");
				
				System.out.println( fileName + " : " + realImgPath);
				System.out.println( "fileSize : " + file.getSize());					
												
			 	try {
			 		// 이미지파일 저장
					file.transferTo(new File(realImgPath, fileName));										
				} catch (Exception e) {
					e.printStackTrace();
				} 
									
			}else{
				// 이미지파일 실패시
				fileName = "FileFail.jpg";
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/" + fileName);
				System.out.println(fileName + " : " + realImgPath);
						
				}			
			
			}
				
			command = new AnPetInsertCommand();
			command.execute(model);
		
			return "cPetInsert";
	
		}	

		//펫정보 가져오기
		@RequestMapping(value="/cPetSelect", method = {RequestMethod.GET, RequestMethod.POST}  )
		public String cPetSelect(HttpServletRequest req, Model model){
		System.out.println("cPetSelect()");
		
		String member_id = (String) req.getParameter("member_id");
		System.out.println(member_id);
		model.addAttribute("member_id", member_id);		
		
		command = new AnPetSelectCommand();
		command.execute(model);
		
		return "cPetSelect";
		}
		
		//펫정보 수정
		@RequestMapping(value="/cPetUpdate", method = {RequestMethod.GET, RequestMethod.POST})
		public void cPetUpdate(HttpServletRequest req, Model model){
			System.out.println("cPetUpdate()");	
			
			try {
				req.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
			String originalName = (String) req.getParameter("originalName");
			String member_id = (String) req.getParameter("member_id");
			String petname = (String) req.getParameter("petname");
			String petage = (String) req.getParameter("petage");
			String petweight = (String) req.getParameter("petweight");
			String petgender = (String) req.getParameter("petgender");
			String pDbImgPath = (String) req.getParameter("pDbImgPath");
			String dbImgPath = (String) req.getParameter("dbImgPath");
			
			System.out.println(originalName);
			System.out.println(member_id);
			System.out.println(petname);
			System.out.println(petage);
			System.out.println(petweight);
			System.out.println(petgender);
			System.out.println("cPetUpdate:dbImgPath " + pDbImgPath);
			System.out.println("cPetUpdate:pDbImgPath " + dbImgPath);
			
			model.addAttribute("originalName",originalName);
			model.addAttribute("member_id", member_id);
			model.addAttribute("petage", petage);
			model.addAttribute("petweight", petweight);
			model.addAttribute("petname", petname);
			model.addAttribute("petgender", petgender);
			model.addAttribute("dbImgPath", dbImgPath);
			
			
			// 이미지가 서로 같으면 삭제하지 않고 다르면 기존이미지 삭제
			if(!dbImgPath.equals(pDbImgPath)){			
				
				String pFileName = req.getParameter("pDbImgPath").split("/")[req.getParameter("pDbImgPath").split("/").length -1];
				String delDbImgPath = req.getSession().getServletContext().getRealPath("/resources/" + pFileName);
				
				File delfile = new File(delDbImgPath);
				System.out.println(delfile.getAbsolutePath());
				
		        if(delfile.exists()) {
		        	boolean deleteFile = false;
		            while(deleteFile != true){
		            	deleteFile = delfile.delete();
		            }	            
		            
		        }//if(delfile.exists())
			
			}//if(!dbImgPath.equals(pDbImgPath))  
			
			MultipartRequest multi = (MultipartRequest)req;
			MultipartFile file = null;
			
			file = multi.getFile("image");
				
			if(file != null) {
				String fileName = file.getOriginalFilename();
				System.out.println(fileName);
				
				// 디렉토리 존재하지 않으면 생성
				makeDir(req);	
					
				if(file.getSize() > 0){			
					String realImgPath = req.getSession().getServletContext()
							.getRealPath("/resources/");
					
					System.out.println( fileName + " : " + realImgPath);
					System.out.println( "fileSize : " + file.getSize());					
													
				 	try {
				 		// 이미지파일 저장
						file.transferTo(new File(realImgPath, fileName));						
					} catch (Exception e) {
						e.printStackTrace();
					} 
										
				}else{
					fileName = "FileFail.jpg";
					String realImgPath = req.getSession().getServletContext()
							.getRealPath("/resources/" + fileName);
					System.out.println(fileName + " : " + realImgPath);
							
				}			
				
			}
			
			command = new AnPetUpateCommand();
			command.execute(model);		
			
		}
	
		//사진변경X 수정
		@RequestMapping(value="/cPetUpdateMultiNo", method = {RequestMethod.GET, RequestMethod.POST})
		public void cPetUpdateMultiNo(HttpServletRequest req, Model model){
			System.out.println("cPetUpdateMultiNo()");	
			
			try {
				req.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}  
			
			String originalName = (String) req.getParameter("originalName");
			String member_id = (String) req.getParameter("member_id");
			String petname = (String) req.getParameter("petname");
			String petage = (String) req.getParameter("petage");
			String petweight = (String) req.getParameter("petweight");
			String petgender = (String) req.getParameter("petgender");
			
			System.out.println(originalName);
			System.out.println(member_id);
			System.out.println(petname);
			System.out.println(petage);
			System.out.println(petweight);
			System.out.println(petgender);
			
			model.addAttribute("originalName",originalName);
			model.addAttribute("member_id", member_id);
			model.addAttribute("petage", petage);
			model.addAttribute("petweight", petweight);
			model.addAttribute("petname", petname);
			model.addAttribute("petgender", petgender);
			
			
			command = new AnPetUpdateMultiNoCommand();
			command.execute(model);		
			
		}

		//펫정보 삭제
		@RequestMapping(value="/cPetDelete", method = {RequestMethod.GET, RequestMethod.POST})
		public void cPetDelete(HttpServletRequest req, Model model){
			System.out.println("cPetDelete()");		
			
			model.addAttribute("member_id", req.getParameter("member_id"));		
			model.addAttribute("petname", req.getParameter("petname"));		
					
			System.out.println((String)req.getParameter("member_id"));
			System.out.println((String)req.getParameter("petname"));
		
			

			command = new AnPetDeleteCommand();
			command.execute(model);	
			
		}
		
}
