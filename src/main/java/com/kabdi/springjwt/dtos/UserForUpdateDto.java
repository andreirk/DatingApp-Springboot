package com.kabdi.springjwt.dtos;

import java.io.Serializable;

public class UserForUpdateDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3251121022073648072L;

	private int id;
	
    private String Introduction;
    
    private String lookingFor;
    
    private String interests;
    
    private String city;
    
    private String country;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIntroduction() {
		return Introduction;
	}

	public void setIntroduction(String introduction) {
		Introduction = introduction;
	}

	public String getLookingFor() {
		return lookingFor;
	}

	public void setLookingFor(String lookingFor) {
		this.lookingFor = lookingFor;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
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
}
