package com.kabdi.springjwt.dtos;

import java.io.Serializable;

public class UserForLoginDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4640436907869533642L;

	
	private String username;

    private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
