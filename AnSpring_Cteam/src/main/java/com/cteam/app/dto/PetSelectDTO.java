package com.cteam.app.dto;

public class PetSelectDTO {
	String date,memo,icon,hour,minute;

	public PetSelectDTO(String date, String memo, String icon, String hour, String minute) {
		super();
		this.date = date;
		this.memo = memo;
		this.icon = icon;
		this.hour = hour;
		this.minute = minute;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	
	
	}
