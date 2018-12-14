package com.kabdi.springjwt.dtos;

import java.io.Serializable;
import java.util.Date;

public class UserForListDto {
	
	/**
	 * 
	 *//*
	private static final long serialVersionUID = 4287400319933398695L;*/

	private int id;
	
	private String username;

	private String gender;
	
	private int age;
	
	private String knownAs;
	
	private Date created;
	    
	private Date lastActive;
	
	private String city;
	    
	private String country;
	
	private String photoUrl;
	
	public UserForListDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserForListDto(int id, String username, String gender, int age, String knownAs, Date created,
			Date lastActive, String city, String country, String photoUrl) {
		super();
		this.id = id;
		this.username = username;
		this.gender = gender;
		this.age = age;
		this.knownAs = knownAs;
		this.created = created;
		this.lastActive = lastActive;
		this.city = city;
		this.country = country;
		this.photoUrl = photoUrl;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getKnownAs() {
		return knownAs;
	}

	public void setKnownAs(String knownAs) {
		this.knownAs = knownAs;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastActive() {
		return lastActive;
	}

	public void setLastActive(Date lastActive) {
		this.lastActive = lastActive;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	
}
