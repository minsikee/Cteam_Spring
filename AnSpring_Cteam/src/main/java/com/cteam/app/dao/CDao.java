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

import com.cteam.app.dto.CalDTO;
import com.cteam.app.dto.MemberDTO1;
import com.cteam.app.dto.PetDTO;
import com.cteam.app.dto.PetSelectDTO;
import com.cteam.app.dto.petPhotoDTO;

public class CDao {

	DataSource dataSource;

	public CDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/cteam");	//context name 이랑 맞춰줘야함 team01로
			
		} catch (NamingException e) {
			e.getMessage();
		}

	}
	
    public MemberDTO1 anLogin(String idin, String passwdin) {
    	
    	//받으면 찍어본다
    	System.out.println(idin);
    	System.out.println(passwdin);

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
	
	
		//캘린더 아이콘 삽입
		public int calInsert(String calendar_date, String calendar_icon, String calendar_memo,String calendar_hour,String calendar_minute,String petname) {
			
			Connection connection = null;
			PreparedStatement prepareStatement = null;
			ResultSet resultSet = null;
			
			int state = -1;
		
			try {
				connection = dataSource.getConnection();
				String query = "insert into calendar(calendar_id,calendar_date, calendar_icon, calendar_memo, calendar_hour, calendar_minute,petname) "
						 + "values(NO_SEQ.NEXTVAL,'" + calendar_date + "','" + calendar_icon + "','" + calendar_memo + "','"+calendar_hour+"','"+calendar_minute+"','"+petname+"')";
				
				
				prepareStatement = connection.prepareStatement(query);
				state = prepareStatement.executeUpdate();
		
				if (state > 0) {
					System.out.println("삽입성공");
					
				} else {
					System.out.println("삽입실패");
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
		public int calUpdate(String calendar_date, String calendar_icon, String calendar_memo,String calendar_hour,String calendar_minute,String calendar_id) {
			
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

		//캘린더 아이콘 선택
		public ArrayList<CalDTO> calSelect(String calendar_date, String petname) {
			
			ArrayList<CalDTO> caldtos = new ArrayList<CalDTO>();
			Connection connection = null;
			PreparedStatement prepareStatement = null;
			ResultSet resultSet = null;		
			
			System.out.println("날짜" + calendar_date);
			
			try {
				connection = dataSource.getConnection();
				String query = "select calendar_date, calendar_icon, calendar_memo,calendar_hour,calendar_minute,calendar_id "					
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

					CalDTO caldto = new CalDTO(calendar_date, calendar_icon, calendar_memo,calendar_hour,calendar_minute,calendar_id);
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
				String query = "select calendar_date, calendar_icon, calendar_memo,calendar_hour,calendar_minute,calendar_id "					
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

					CalDTO caldto = new CalDTO(calendar_date, calendar_icon, calendar_memo, calendar_hour, calendar_minute,calendar_id);
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

		public ArrayList<petPhotoDTO> cPhotoSelect(String petName, String member_id) {
			ArrayList<petPhotoDTO> cdtos = new ArrayList<petPhotoDTO>();
			Connection connection = null;
			PreparedStatement prepareStatement = null;
			ResultSet resultSet = null;		
			
			try {
				connection = dataSource.getConnection();
				String query = "select *"				
								+ " from petphoto"
								+ " where petname='"+petName+"' and "
								+ " member_id='"+member_id+"'"
								+"order by petphoto_no desc";
				
				prepareStatement = connection.prepareStatement(query);
				resultSet = prepareStatement.executeQuery();
				
				while (resultSet.next()) {
					String petPhoto_imgpath = resultSet.getString("petPhoto_imgpath");
					String petPhoto_content = resultSet.getString("petPhoto_content"); 
					String petPhoto_date = resultSet.getString("petPhoto_date"); 
					String petName2 = resultSet.getString("petName");
					int petPhoto_no=resultSet.getInt("petPhoto_no");

					petPhotoDTO cdto = new petPhotoDTO(petPhoto_imgpath,petPhoto_content,petPhoto_date,petName2,petPhoto_no);
					cdtos.add(cdto);			
					
				}	
				
				System.out.println("adtos크기" + cdtos.size());
				
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

			return cdtos;

	
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
		
		//진 - 로그인dto 업데이트
		public MemberDTO1 anUpdateLogin(String id) {
			
			MemberDTO1 cdto = null;
			Connection connection = null;
			PreparedStatement prepareStatement = null;
			ResultSet resultSet = null;		
			
			try {
				connection = dataSource.getConnection();
				String query = "select * " + " from memberjoin" + " where member_id = '" + id + "'";
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

		public int cPetInsert(String member_id, String petname, String petage, String petweight, String petgender,
				String petimagepath) {
			Connection connection = null;
			PreparedStatement prepareStatement = null;
			ResultSet resultSet = null;
			
			int state = -1;
		
			try {						
				connection = dataSource.getConnection();
				String query = "insert into petadd (member_id, petname, petage, petweight, petgender, petimagepath) values('" + member_id + "','"+ petname + "','"+ petage + "','"+ petweight + "','"+ petgender +"','"+ petimagepath +"' )";
				
				prepareStatement = connection.prepareStatement(query);
				state = prepareStatement.executeUpdate();
		
				if (state > 0) {
					System.out.println("삽입성공");
					
				} else {
					System.out.println("삽입실패");
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

		//진 - 펫추가에서 펫정보 가져오기
		public ArrayList<PetDTO> cPetSelect(String id) {
			ArrayList<PetDTO> dto = new ArrayList<PetDTO>();
			Connection connection = null;
			PreparedStatement prepareStatement = null;
			ResultSet resultSet = null;
			
			try {
				connection = dataSource.getConnection();
				String query = "select * "//이미지는 경로로 저장					
								+ " from petadd where member_id = '" + id + "'";
				prepareStatement = connection.prepareStatement(query);
				resultSet = prepareStatement.executeQuery();
				
				while (resultSet.next()) {

					String petname = resultSet.getString("petname");
					String petage = resultSet.getString("petage");
					String petweight = resultSet.getString("petweight");
					String petgender = resultSet.getString("petgender");
					//String member_id = resultSet.getString("member_id");
					String petimagepath = resultSet.getString("petimagepath");

					PetDTO pdto = new PetDTO(petname, petage, petweight, petgender, petimagepath);
					dto.add(pdto);		
				}	
				
				System.out.println("dto크기" + dto.size());

				
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

			
			return dto;
		}

		
		//수안- 사진첩 사진 삽입
		public int PetPhotoInsert(String member_id, String petName, String petPhoto_content, String imageDbPathA) {

			Connection connection = null;
			PreparedStatement prepareStatement = null;
			ResultSet resultSet = null;
			
			int state = -1;
		
			try {						
				connection = dataSource.getConnection();
				String query = "insert into petphoto (petphoto_no,petname, petphoto_imgpath, petphoto_content, petphoto_date,member_id) values"
						+"(petphoto_seq.nextval, '" + petName + "','"+ imageDbPathA + "','"+ petPhoto_content + "',sysdate,'"+ member_id + "' )";
				
				prepareStatement = connection.prepareStatement(query);
				state = prepareStatement.executeUpdate();
		
				if (state > 0) {
					System.out.println("삽입성공");
					
				} else {
					System.out.println("삽입실패");
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

		public int petPhotoDelete(String petPhoto_no) {

			
			Connection connection = null;
			PreparedStatement prepareStatement = null;
			ResultSet resultSet = null;
			
			int state = -1;

			try {
				connection = dataSource.getConnection();
				String query = "delete from petphoto where petphoto_no='"+ petPhoto_no +"'";
				
				System.out.println(petPhoto_no);

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

		public int petPhotoUpdateMulti(String petPhoto_no, String petPhoto_content, String dbImgPath) {

			
			
			Connection connection = null;
			PreparedStatement prepareStatement = null;
			ResultSet resultSet = null;
			
			int state = -1;
		
			try {			
				// 아이디는 수정할수 없음			
				connection = dataSource.getConnection();
				String query = "update petphoto set " 			             
			             + " petphoto_content = '" + petPhoto_content + "' "
			             + ",petphoto_imgpath  = '" + dbImgPath + "' "
						 + " where petphoto_no = '"+petPhoto_no+"'" ;
				
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

		public int petPhotoUpdateMultiNo(String petPhoto_no, String petPhoto_content) {

			Connection connection = null;
			PreparedStatement prepareStatement = null;
			ResultSet resultSet = null;
			
			int state = -1;
		
			try {			
				// 아이디는 수정할수 없음			
				connection = dataSource.getConnection();
				String query = "update petphoto set " 			             
			             + " petphoto_content = '" + petPhoto_content + "' "
						 + " where petphoto_no = '"+petPhoto_no+"'" ;
				
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
		
		
		
	
	}
  
	

  

    
    

