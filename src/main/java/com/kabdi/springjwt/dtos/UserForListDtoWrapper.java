package com.kabdi.springjwt.dtos;


public class UserForListDtoWrapper {
	
	private String token;
	private UserForListDto user;
	
	public UserForListDtoWrapper(String token, UserForListDto user) {
		super();
		this.token = token;
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setJwt(String token) {
		this.token = token;
	}
	public UserForListDto getUser() {
		return user;
	}
	public void setUserForListDto(UserForListDto userForListDto) {
		this.user = user;
	}
	

}
