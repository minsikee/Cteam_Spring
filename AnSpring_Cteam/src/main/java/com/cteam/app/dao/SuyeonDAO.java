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
import com.cteam.app.dto.CalDTO;

public class SuyeonDAO {
	
	DataSource dataSource;
	
	public SuyeonDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/cteam");
			
		} catch (NamingException e) {
			e.getMessage();
		}
	}
	
	//캘린더 아이콘 선택
	public ArrayList<CalDTO> calSelect(String calendar_date, String petname) {
		
		ArrayList<CalDTO> caldtos = new ArrayList<CalDTO>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		System.out.println("날짜" + calendar_date);
		
		try {
			connection = dataSource.getConnection();
			String query = "select calendar_date, calendar_icon, calendar_memo, calendar_hour, calendar_minute, calendar_id "					
							+ " from calendar" 
							+ " where calendar_date='" + calendar_date + "'"
							+ " and petname='" + petname + "'"
							+ " order by calendar_id";
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				calendar_date = resultSet.getString("calendar_date");
				String calendar_icon = resultSet.getString("calendar_icon");
				String calendar_memo = resultSet.getString("calendar_memo");
				String calendar_hour = resultSet.getString("calendar_hour");
				String calendar_minute = resultSet.getString("calendar_minute");
				String calendar_id = resultSet.getString("calendar_id");

				CalDTO caldto = new CalDTO(calendar_date, calendar_icon, calendar_memo, calendar_hour, calendar_minute, calendar_id);
				caldtos.add(caldto);			
			}	
			
			System.out.println("caldto크기" + caldtos.size());
			
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
		
		return caldtos;
		
	} //calSelect()
	
	//캘린더 아이콘 추가
	public int calInsert(String calendar_date, String calendar_icon, String calendar_memo, String calendar_hour, String calendar_minute, String petname) {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		int state = -1;
	
		try {
			connection = dataSource.getConnection();
			String query = "insert into calendar(calendar_id,calendar_date, calendar_icon, calendar_memo, calendar_hour, calendar_minute, petname) "
					 + "values(NO_SEQ.NEXTVAL,'" + calendar_date + "','" + calendar_icon + "','" + calendar_memo + "','"+calendar_hour+"','"+calendar_minute+"','"+petname+"')";
			
			
			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();
	
			if (state > 0) {
				System.out.println("추가성공");
				
			} else {
				System.out.println("추가실패");
			}
	
		} catch (Exception e) {
			e.printStackTrace();
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
	
	} //calUpdate()
    
	//캘린더 아이콘 변경
	public int calUpdate(String calendar_date, String calendar_icon, String calendar_memo, String calendar_hour, String calendar_minute, String calendar_id) {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		int state = -1;
	
		try {		
			connection = dataSource.getConnection();
			String query = "update calendar set " 			             
		             + "calendar_icon = '" + calendar_icon + "' "
		             + ", calendar_memo = '" + calendar_memo + "' "
		             + ", calendar_hour = '" + calendar_hour + "' "
		             + ", calendar_minute = '" + calendar_minute + "' "
					 + " where calendar_id = '" +  calendar_id+"' ";
			
			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();
	
			if (state > 0) {
				System.out.println("수정성공");
				
			} else {
				System.out.println("수정실패");
			}
	
		} catch (Exception e) {
			e.printStackTrace();
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
		
	return state;
	
	} //calUpdate()
	
	//캘린더 아이콘 삭제
	public int calDelete(String calendar_id) {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		int state = -1;

		try {
			connection = dataSource.getConnection();
			String query = "delete from calendar where calendar_id='" + calendar_id +"'";
			
			System.out.println(calendar_id);

			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();

			if (state > 0) {
				System.out.println("삭제성공");				
			} else {
				System.out.println("삭제실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
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

	} //calDelete()
	
	
	//캘린더 아이콘 붙이기
	public ArrayList<CalDTO> calcalSelect(String petname) {
		
		ArrayList<CalDTO> caldtos = new ArrayList<CalDTO>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		try {
			connection = dataSource.getConnection();
			String query = "select calendar_date, calendar_icon, calendar_memo, calendar_hour, calendar_minute, calendar_id "					
							+ " from calendar"
							+ " where petname='" + petname + "'"
							+ " order by calendar_date, calendar_id asc";
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				String calendar_date = resultSet.getString("calendar_date");
				String calendar_icon = resultSet.getString("calendar_icon");
				String calendar_memo = resultSet.getString("calendar_memo");
				String calendar_hour = resultSet.getString("calendar_hour");
				String calendar_minute = resultSet.getString("calendar_minute");
				String calendar_id = resultSet.getString("calendar_id");

				CalDTO caldto = new CalDTO(calendar_date, calendar_icon, calendar_memo, calendar_hour, calendar_minute, calendar_id);
				caldtos.add(caldto);			
			}	
			
			System.out.println("caldto크기" + caldtos.size());
			
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
		
		return caldtos;
		
	} //calSelect()
	
	//내가 쓴 게시물 불러오기
	public ArrayList<BoardselectDTO> myPostingSelect() {
		
		ArrayList<BoardselectDTO> myboardselectdto = new ArrayList<BoardselectDTO>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		try {
			connection = dataSource.getConnection();
			String query = "select member_id,board_subject,board_title,board_date,board_comment,board_num"					
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
				
				BoardselectDTO myboardselect = new BoardselectDTO(id, subject, title, date, comment, num); 
				myboardselectdto.add(myboardselect);

			}	
			
			System.out.println("boardselectdto크기" + myboardselectdto.size());
			System.out.println("boardselectdto크기" + myboardselectdto);
			
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

		return myboardselectdto;
	}

}
