package com.cteam.app.dto;

public class BoardselectDTO {
	String id, subject, title, date,comment;
	int num;
	public BoardselectDTO(String id, String subject, String title, String date,String comment, int num ) {
		super();
		this.id = id;
		this.subject = subject;
		this.title = title;
		this.date = date;
		this.comment = comment;
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	
}
