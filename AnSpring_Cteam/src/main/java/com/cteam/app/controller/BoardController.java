package com.cteam.app.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.cteam.app.command.AnCommand;
import com.cteam.app.command.BoardDetailCommand;
import com.cteam.app.command.BoardSelectCommand;
import com.cteam.app.command.boardinsertCommand;

@Controller
public class BoardController {
	
	AnCommand command;

	//게시판 메인
	@RequestMapping(value="/boardselect", method = {RequestMethod.GET, RequestMethod.POST} )
	public String boardselect(HttpServletRequest req, Model model) {
		System.out.println("컨트롤러 : boardselect()");
		
		command = new BoardSelectCommand();
		command.execute(model);
		
		return "board/boardselect";
	}
	
	
	//게시판 글쓰기 요청
	@RequestMapping(value="/boardinsert", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String boardinsert(HttpServletRequest req, Model model){
		System.out.println("boardinsert()");	
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 

		String member_id = (String) req.getParameter("member_id");
		String board_subject = (String) req.getParameter("subject");
		String board_title = (String) req.getParameter("title");
		String board_content = (String) req.getParameter("content");
		String board_city = (String) req.getParameter("city");
		String board_region = (String) req.getParameter("region");
		String board_imagepath = (String) req.getParameter("dbImgPath");
		String Petimage_path = (String) req.getParameter("Petimage_path");



		System.out.println("컨트롤러boardinsert : " +member_id+board_subject+board_title+
				board_content+board_city+board_region+board_imagepath+Petimage_path);
		
		model.addAttribute("member_id", member_id);
		model.addAttribute("board_subject", board_subject);
		model.addAttribute("board_title", board_title);
		model.addAttribute("board_content", board_content);
		model.addAttribute("board_city", board_city);
		model.addAttribute("board_region", board_region);
		model.addAttribute("board_imagepath", board_imagepath);
		model.addAttribute("Petimage_path", Petimage_path);
	
		MultipartRequest multi = (MultipartRequest) req;
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
			
			
		command = new boardinsertCommand();
		command.execute(model);

		return "board/boardinsert";
	}

	//게시판 상세보기 요청
	@RequestMapping("/boarddetail")
	public String boarddetail(HttpServletRequest req, Model model) {
		System.out.println("boarddetail()");

		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		int board_num = Integer.parseInt(req.getParameter("num"));

		System.out.println("컨트롤러 boarddetail :" + board_num);

		model.addAttribute("board_num", board_num);

		command = new BoardDetailCommand();
		command.execute(model);

		return "board/boarddetail";
	}
	
	
	
	
	public void makeDir(HttpServletRequest req){
		File f = new File(req.getSession().getServletContext().getRealPath("/resources"));
		if(!f.isDirectory()){
		f.mkdir();
		}	
	}
	
	
}
