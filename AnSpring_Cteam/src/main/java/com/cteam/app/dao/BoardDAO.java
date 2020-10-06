package com.cteam.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.cteam.app.dto.BoarddetailDTO;
import com.cteam.app.dto.BoardselectDTO;
import com.cteam.app.dto.CalDTO;

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
	public int boardinsert(String member_id, String board_subject, String board_title, String board_content, String board_city, String board_region, String board_imagepath, String Petimage_path) {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
				
		int state = -1;
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into board(board_num,member_id, board_subject, board_title, board_content, board_city, board_region, board_imagepath, Petimage_path)"
					+ "values(board_seq.nextval, '" + member_id + "','" + board_subject + "','" + board_title + "','"  + board_content + "','" 
					+  board_city +"','" + board_region + "','" + board_imagepath + "','" + Petimage_path + "' )" ;
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
			String query = "select member_id,board_subject,board_title,board_city,board_region,board_date,board_num"//이미지는 경로로 저장					
							+ " from board" 
							+ " order by board_date desc";
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				String id = resultSet.getString("member_id");
				String subject = resultSet.getString("board_subject");
				String title = resultSet.getString("board_title");
				String city = resultSet.getString("board_city");
				String region = resultSet.getString("board_region");
				String date = resultSet.getString("board_date"); 
				int num = resultSet.getInt("board_num");

				System.out.println("받았는지" + id+subject+title+city+region+date+num);
				
				BoardselectDTO boardselect = new BoardselectDTO(id, subject, title, city, region, date, num); 
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
	public ArrayList<BoarddetailDTO> boarddetail(int board_num) {
		
		ArrayList<BoarddetailDTO> detaildto = new ArrayList<BoarddetailDTO>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		System.out.println("번호" + board_num);
	
		
		try {
			connection = dataSource.getConnection();
			String query = "select board_num, board_subject, board_title, board_content, member_id, petname, board_date, board_city," 
							+ " board_region, board_content, board_imagepath, Petimage_path"
							//+ " (select petimagepath from petadd p where p.member_id=b.member_id and p.petname = b.petname) petimagepath"
							+ "from board b"
							+ "where board_num=?";
			
			
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				board_num = Integer.parseInt(resultSet.getString("board_num"));
				String board_subject = resultSet.getString("board_subject");
				String board_title = resultSet.getString("board_title");
				String board_content = resultSet.getString("board_content");
				String member_id = resultSet.getString("member_id");
				String petname = resultSet.getString("petname");
				String board_date = resultSet.getString("board_date");
				String board_imagepath = resultSet.getString("board_imagepath");
				String board_city = resultSet.getString("board_city");
				String board_region = resultSet.getString("board_region");
				String petimagepath = resultSet.getString("petimagepath");

				BoarddetailDTO detaildtos = new BoarddetailDTO(board_num, board_subject, board_title, board_content, member_id, petname, board_date, board_imagepath,board_city, board_region, petimagepath);
				detaildto.add(detaildtos);
			}
			
			System.out.println("detaildto크기" + detaildto.size());
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
		
		return detaildto;
	}

}
