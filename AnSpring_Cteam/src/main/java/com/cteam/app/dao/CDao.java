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
				String member_name = resultSet.getString("member_name");
				String member_question = resultSet.getString("member_question"); 
				String member_answer = resultSet.getString("member_answer");
				String member_phonenum = resultSet.getString("member_phonenum");
				cdto = new MemberDTO1(member_id, member_name, member_question,member_answer, member_phonenum);							
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
    
}
