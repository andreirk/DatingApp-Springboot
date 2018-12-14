package com.kabdi.springjwt.helper;

public class UserParams {
	
	private int userId;
	
	private String gender;
	
	private int minAge;
	
	private int maxAge;
	
	private String orderBy;
	
	private boolean likees;
	
	private boolean likers;
	

	public UserParams(int userId, String gender, int minAge, int maxAge, String orderBy, boolean likees,
			boolean likers) {
		super();
		this.userId = userId;
		this.gender = gender;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.orderBy = orderBy;
		this.likees = likees;
		this.likers = likers;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isLikees() {
		return likees;
	}

	public void setLikees(boolean likees) {
		this.likees = likees;
	}

	public boolean isLikers() {
		return likers;
	}

	public void setLikers(boolean likers) {
		this.likers = likers;
	}
	
	

}
