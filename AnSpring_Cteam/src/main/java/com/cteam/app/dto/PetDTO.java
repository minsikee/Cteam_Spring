package com.cteam.app.dto;

public class PetDTO {
	private String petname;
	private String petage;
	private String petweight;
	private String petgender;
//	private String member_id;
	private String petimagepath;
	
	public PetDTO(String petname, String petage, String petweight, String petgender, String petimagepath ) {
		super();
		this.petname = petname;
		this.petage = petage;
		this.petweight = petweight;
		this.petgender = petgender;
		//this.member_id = member_id;
		this.petimagepath = petimagepath;
	}

	public String getPetname() {
		return petname;
	}

	public void setPetname(String petname) {
		this.petname = petname;
	}

	public String getPetage() {
		return petage;
	}

	public void setPetage(String petage) {
		this.petage = petage;
	}

	public String getPetweight() {
		return petweight;
	}

	public void setPetweight(String petweight) {
		this.petweight = petweight;
	}

	public String getPetgender() {
		return petgender;
	}

	public void setPetgender(String petgender) {
		this.petgender = petgender;
	}

	/*
	 * public String getMember_id() { return member_id; }
	 * 
	 * public void setMember_id(String member_id) { this.member_id = member_id; }
	 */

	public String getPetimagepath() {
		return petimagepath;
	}

	public void setPetimagepath(String petimagepath) {
		this.petimagepath = petimagepath;
	}
	
	
}
