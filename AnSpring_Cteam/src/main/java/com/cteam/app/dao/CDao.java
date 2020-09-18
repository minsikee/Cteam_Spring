package com.cteam.app.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.cteam.app.dto.MemberDTO1;
import com.cteam.app.dto.PetSelectDTO;

public class CDao {

	DataSource dataSource;

	public CDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/cteam");	//context name 이랑 맞춰줘야함 team01로
			//dataSource = (DataSource) context.lookup("java:/comp/env/cteam");
			/*dataSource = (DataSource) context.lookup("java:/comp/env/CSS");*/
		} catch (NamingException e) {
			e.getMessage();
		}

	}
	
    public MemberDTO1 anLogin(String idin, String passwdin) {

    	MemberDTO1 cdto = null;
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		try {
			connection = dataSource.getConnection();
			String query = "select * "					
							+ " from memberjoin" 
							+ " where member_id = '" + idin + "' and member_pw = '" + passwdin + "' ";
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				String member_id = resultSet.getString("member_id");
				String member_pw = resultSet.getString("member_pw");
				String member_name = resultSet.getString("member_name");
				String member_question = resultSet.getString("member_question"); 
				String member_answer = resultSet.getString("member_answer");
				String member_phonenum = resultSet.getString("member_phonenum");
				cdto = new MemberDTO1(member_id, member_pw, member_name, member_question,member_answer, member_phonenum);							
			}	
			
			System.out.println("MemberDTO id : " + cdto.getMember_id());
			
			
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

		return cdto;

	}
    
	public int cJoin(String member_id, String member_pw, String member_name, 
			String member_question,String member_answer,String member_phonenum) {

		Connection connection = null;
		PreparedStatement prepareStatement = null;
		int state = -100;
		//db에 삽입성공하면 1이 넘어온다(0보다 큰 값) 삽입 실패시 0이나 -1 이 넘어온다.

		try {
			connection = dataSource.getConnection();
			String query = "insert into memberjoin(member_id, member_pw, member_name, member_question,member_answer,member_phonenum) " + 
			"values('" + member_id + "', '"
					+ member_pw + "', '" + member_name + "', '" + member_question + "', '" 
			+ member_answer + "', '"+ member_phonenum + "' )";
			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();

			if (state > 0) {
				System.out.println(state + "삽입성공");
			} else {
				System.out.println(state + "삽입실패");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
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
		

	}
	
    
	public String cFind(String member_name, String member_phonenum) {

		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		String id_return = null;

		try {
			connection = dataSource.getConnection();
			String query = "select member_id "					
					+ " from memberjoin" 
					+ " where member_name = '" + member_name + "' and member_phonenum = '" + member_phonenum + "' ";
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				id_return = resultSet.getString("member_id");
						
			}	
			
			System.out.println("member_id : " + id_return);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
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

		return id_return;
		

	}

    
	public String cPwFind(String member_id, String member_question,String member_answer) {

		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		String pw_return = null;

		try {
			connection = dataSource.getConnection();
			String query = "select member_pw "					
					+ " from memberjoin" 
					+ " where member_id = '" + member_id + "' and member_question = '" + member_question + "' and member_answer = '" + member_answer + "' ";
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				pw_return = resultSet.getString("member_pw");
						
			}	
			
			System.out.println("member_pw : " + pw_return);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
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

		return pw_return;
		

	}

	public int cPwUpdate(String member_id, String member_pw) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		int state = -100;
		//db에 삽입성공하면 1이 넘어온다(0보다 큰 값) 삽입 실패시 0이나 -1 이 넘어온다.

		try {
			connection = dataSource.getConnection();
			String query = "update memberjoin set member_pw='"+ member_pw + 
					 "' where member_id='"+member_id+"'";
			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();

			if (state > 0) {
				System.out.println(state + "삽입성공");
			} else {
				System.out.println(state + "삽입실패");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
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
		

	}
	

	public ArrayList<PetSelectDTO> petSelectMulti() {
 
			
			ArrayList<PetSelectDTO> adtos = new ArrayList<PetSelectDTO>();
			Connection connection = null;
			PreparedStatement prepareStatement = null;
			ResultSet resultSet = null;		
			
			try {
				connection = dataSource.getConnection();
				String query = "select calendar_date,calendar_memo, calendar_icon, calendar_hour, calendar_minute "//이미지는 경로로 저장					
								+ " from calendar" 
								+ " order by calendar_hour desc";
				prepareStatement = connection.prepareStatement(query);
				resultSet = prepareStatement.executeQuery();
				
				while (resultSet.next()) {
					String date=resultSet.getString("calendar_date");
					String memo = resultSet.getString("calendar_memo");
					String icon = resultSet.getString("calendar_icon");
					String hour = resultSet.getString("calendar_hour"); 
					String minute = resultSet.getString("calendar_minute"); 

					
					  PetSelectDTO adto = new PetSelectDTO(date, memo, icon, hour,minute); 
					  adtos.add(adto);
						
				}	
				
				System.out.println("adtos크기" + adtos.size());
				System.out.println("adtos크기" + adtos.get(12).getIcon());
				
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

			return adtos;

		}
	
	
public int cPetbarInsertMulti(String date,String memo,String icon, String hour, String minute) {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
				
		int state = -1;

		try {			
			// 
			connection = dataSource.getConnection();
			String query = "insert into calendar (calendar_date,calendar_memo,calendar_icon,calendar_hour, calendar_minute) " + 
			"values('"+date +"','"+ memo +"' , '"+ icon +"','"+ hour +"','"+ minute +"')";

			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();
			
			if (state > 0) {
				System.out.println(state + "삽입성공");				
			} else {
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
	
	//진 - 업데이트
	public int cModify(String member_id, String member_pw, String member_name, String member_question, String member_answer, String member_phonenum) {
		Connection connection = null;
		
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		String pw_return = null;
		
		int state = -1;
		
		try {
			connection = dataSource.getConnection();
			String query = "update memberjoin set member_pw='"+member_pw+"', member_question ='"+member_question+"',"
					+"member_answer='"+member_answer+"', member_phonenum='"+member_phonenum+"' where member_id ='"+member_id+"'";
			
			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();


			if (state > 0) {
				System.out.println("수정1성공");
				
			} else {
				System.out.println("수정1실패");
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
			} finally {
	
			}
		}
		
		return state;
	}
  
	
}
  

    
    

