package com.cteam.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.cteam.app.dto.BoardselectDTO;

public class BoardDAO {
	
	DataSource dataSource;
	
	public BoardDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/cteam");	//context name 이랑 맞춰줘야함 team01로
			
		} catch (NamingException e) {
			e.getMessage();
		}

	}
	
	//게시판 글쓰기
	public int boardinsert(String member_id, String board_subject, String board_title, String board_content, String board_city, String board_region, String board_imagepath) {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
				
		int state = -1;
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into board(board_num,member_id, board_subject, board_title, board_content, board_city, board_region, board_imagepath)"
					+ "values(board_seq.nextval, '" + member_id + "','" + board_subject + "','" + board_title + "','"  + board_content + "','" 
					+  board_city +"','" + board_region + "','" + board_imagepath + "' )" ;
			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();
			
			if( state > 0 ) {
				System.out.println(state + "삽입성공");
			}else {
				System.out.println(state + "삽입실패");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (connection != null) {
					connection.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			} 

		}

		return state;
	}
	
	//게시판 글선택
	public ArrayList<BoardselectDTO> boardselect() {
		
		ArrayList<BoardselectDTO> boardselectdto = new ArrayList<BoardselectDTO>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		try {
			connection = dataSource.getConnection();
			String query = "select member_id,board_subject,board_title,board_date,board_comment,board_num"//이미지는 경로로 저장					
							+ " from board" 
							+ " order by board_date desc";
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				String id = resultSet.getString("member_id");
				String subject = resultSet.getString("board_subject");
				String title = resultSet.getString("board_title");
				String date = resultSet.getString("board_date"); 
				String comment = resultSet.getString("board_comment"); 
				int num = resultSet.getInt("board_num");

				System.out.println("받았는지"+comment);
				
				BoardselectDTO boardselect = new BoardselectDTO(id, subject, title, date,comment,num); 
				boardselectdto.add(boardselect);

			}	
			
			System.out.println("boardselectdto크기" + boardselectdto.size());
			System.out.println("boardselectdto크기" + boardselectdto);
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		} finally {
			try {			
				
				if (resultSet != null) {
					resultSet.close();
				}
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (connection != null) {
					connection.close();
				}	

			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {

			}
		}

		return boardselectdto;
	}

	//게시판 상세보기
	public String boarddetail() {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from board where id=?";
		} catch (Exception e) {

		}
		
		return null;
	}
}
