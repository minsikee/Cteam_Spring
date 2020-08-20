package com.cteam.app.dto;

public class MemberDTO1 {
	String member_id, member_pw, member_name, 
	member_question,member_answer,member_phonenum;

	// �α����Ҷ� ��й�ȣ ���� ������� ������
	public MemberDTO1(String member_id, String member_name, 
			String member_question,String member_answer,String member_phonenum) {
		super();
		this.member_id = member_id;
		this.member_name = member_name;
		this.member_question =member_question ;
		this.member_answer=member_answer;
		this.member_phonenum=member_phonenum;
		
	}

	// �����ͺ��̽��� ������� �߰��Ҷ�
	public MemberDTO1(String member_id, String member_name, String member_pw,
			String member_question,String member_answer,String member_phonenum) {
		super();
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_name = member_name;
		this.member_question =member_question ;
		this.member_answer=member_answer;
		this.member_phonenum=member_phonenum;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_pw() {
		return member_pw;
	}

	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_question() {
		return member_question;
	}

	public void setMember_question(String member_question) {
		this.member_question = member_question;
	}

	public String getMember_answer() {
		return member_answer;
	}

	public void setMember_answer(String member_answer) {
		this.member_answer = member_answer;
	}

	public String getMember_phonenum() {
		return member_phonenum;
	}

	public void setMember_phonenum(String member_phonenum) {
		this.member_phonenum = member_phonenum;
	}


	

}
