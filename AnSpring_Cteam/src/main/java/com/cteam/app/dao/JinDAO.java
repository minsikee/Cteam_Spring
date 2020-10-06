package com.cteam.app.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



public class JinDAO {
	
	
	DataSource dataSource;
	

	public JinDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/cteam");	//context name 이랑 맞춰줘야함 team01로
			
		} catch (NamingException e) {
			e.getMessage();
		}

	}
	
	//진 - 펫정보 업데이트 
	public int cPetUpdate(String originalName, String member_id, String petname, String petage, String petweight, String petgender,
			String dbImgPath) {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		int state = -1;
		
		
		System.out.println(petweight+petgender+dbImgPath+member_id+petname);
		try {			
			// 아이디는 수정할수 없음			
			connection = dataSource.getConnection();
			String query = "update petadd set petname = '" + petname + "' "
					+ ", petage = '" + petage + "' "
		             + ", petweight = '" + petweight + "' "
		             + ", petgender = '" + petgender + "' "
		             + ", petimagepath = '" + dbImgPath + "' "
					 + " where member_id = '"+member_id+"' and petname='"+originalName+"'";
			
			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();
	
			if (state > 0) {
				System.out.println("수정1성공");
				
			} else {
				System.out.println("수정1실패");
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
	
	}

	//진 - 사진 변경 없이 수정하기
	public int cPetUpdateMultiNo(String originalName, String member_id, String petname, String petage,
			String petweight, String petgender) {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		int state = -1;
	
		try {			
			// 아이디는 수정할수 없음			
			connection = dataSource.getConnection();
			String query = "update petadd set petname = '" + petname + "' "
					+ ", petage = '" + petage + "' "
		             + ", petweight = '" + petweight + "' "
		             + ", petgender = '" + petgender + "' "
					 + " where member_id = '"+member_id+"' and petname='"+originalName+"'";
			
			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();
	
			if (state > 0) {
				System.out.println("수정2성공");
				
			} else {
				System.out.println("수정2실패");
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
		
	}

	public int cPetDelete(String member_id, String petname) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		int state = -1;

		try {
			connection = dataSource.getConnection();
			String query = "delete from petadd where member_id='" + member_id+"'"
					+"and petname='"+petname+"'";
			
			System.out.println(member_id);
			System.out.println(petname);

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

	}
	

		
}
