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
import com.cteam.app.command.CalcalSelectCommand;
import com.cteam.app.command.PetPhotoInsertCommand;
import com.cteam.app.command.cPetPhotoSelectCommand;
import com.cteam.app.command.cPetbarInsertCommand;
import com.cteam.app.command.petPhotoDeleteCommand;
import com.cteam.app.command.petPhotoUpdateMultiCommand;
import com.cteam.app.command.petPhotoUpdateMultiNoCommand;
import com.cteam.app.command.petSelectMultiCommand;
import com.cteam.app.command.jin.AnPetInsertCommand;

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
		model.addAttribute("petname", req.getParameter("petname"));	

		command = new CalSelectCommand();
		command.execute(model);
		
		
		return "calSelect";
	} //calSelect()
			

	//
		//갤러리(피드) 셀렉트
		
		@RequestMapping(value="/cPetPhotoSelect", method = {RequestMethod.GET, RequestMethod.POST}  )
		public String anSelectMulti(HttpServletRequest req, Model model){
			System.out.println("cPetPhotoSelectMulti()");
			
			String petName = (String) req.getParameter("petName");
			String member_id = (String) req.getParameter("member_id");
			
			//받으면 찍어본다
			System.out.println(member_id);
			System.out.println(petName);
			
			model.addAttribute("member_id", member_id);
			model.addAttribute("petName", petName);
			
			
			command = new cPetPhotoSelectCommand();
			command.execute(model);
			
			return "cPetPhotoSelect";
		}
		
		
		//사진첩 사진 등록
		@RequestMapping(value="/PetPhotoInsert", method = {RequestMethod.GET, RequestMethod.POST}  )
		public String cPetInsert(HttpServletRequest req, Model model){
			System.out.println("PetPhotoInsert()");	
			
			try {
				req.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 		
			
			String petName = (String) req.getParameter("petName");
			String member_id = (String) req.getParameter("member_id");
			String PetPhoto_content = (String) req.getParameter("PetPhoto_content");
			String imageDbPathA = (String) req.getParameter("imageDbPathA");
			
		
			
			System.out.println(petName);
			System.out.println(member_id);
			System.out.println(PetPhoto_content);
			System.out.println(imageDbPathA);
			
			model.addAttribute("petName", petName);
			model.addAttribute("member_id", member_id);
			model.addAttribute("PetPhoto_content", PetPhoto_content);	
			model.addAttribute("imageDbPathA", imageDbPathA);	
	

			
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
					
				command = new PetPhotoInsertCommand();
				command.execute(model);
			
				return "PetPhotoInsert";
		
			}	
		
		//사진첩 게시글 삭제
		@RequestMapping(value="/petPhotoDelete", method = {RequestMethod.GET, RequestMethod.POST})
		public void petPhotoDelete(HttpServletRequest req, Model model){
			System.out.println("petPhotoDelete()");		
			
			model.addAttribute("petPhoto_no", req.getParameter("petPhoto_no"));		
					
			System.out.println((String)req.getParameter("petPhoto_no"));
			
			command = new petPhotoDeleteCommand();
			command.execute(model);	
			
		}
		

		//디렉토리가 존재하지 않을 때(이미지첨부시사용)
		public void makeDir(HttpServletRequest req){
			File f = new File(req.getSession().getServletContext()
					.getRealPath("/resources"));
			if(!f.isDirectory()){
			f.mkdir();
			}	
		}
		
		@RequestMapping(value="/petPhotoUpdateMulti", method = {RequestMethod.GET, RequestMethod.POST})
		public void anUpdateMulti(HttpServletRequest req, Model model){
			System.out.println("petPhotoUpdateMulti()");	
			
			try {
				req.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
			String petPhoto_no = (String) req.getParameter("petPhoto_no");
			String petPhoto_content = (String) req.getParameter("petPhoto_content");
			String dbImgPath = (String) req.getParameter("dbImgPath");
			String pDbImgPath = (String) req.getParameter("pDbImgPath");
			
			System.out.println(petPhoto_no);
			System.out.println(petPhoto_content);
			System.out.println("Sub1Update:dbImgPath " + dbImgPath);
			System.out.println("Sub1Update:pDbImgPath " + pDbImgPath);
			
			model.addAttribute("petPhoto_no", petPhoto_no);
			model.addAttribute("petPhoto_content", petPhoto_content);
			model.addAttribute("dbImgPath", dbImgPath);
			model.addAttribute("pDbImgPath", pDbImgPath);
			
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
			
			command = new petPhotoUpdateMultiCommand();
			command.execute(model);		
			
		}
		
		@RequestMapping(value="/petPhotoUpdateMultiNo", method = {RequestMethod.GET, RequestMethod.POST})
		public void anUpdateMultiNo(HttpServletRequest req, Model model){
			System.out.println("anUpdateMultiNo()");	
			
			try {
				req.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
			String petPhoto_no = (String) req.getParameter("petPhoto_no");
			String petPhoto_content = (String) req.getParameter("petPhoto_content");	
			
			model.addAttribute("petPhoto_no", petPhoto_no);
			model.addAttribute("petPhoto_content", petPhoto_content);
			
			command = new petPhotoUpdateMultiNoCommand();
			command.execute(model);		
			
		}
			
		
		
		
		
		
	

}
