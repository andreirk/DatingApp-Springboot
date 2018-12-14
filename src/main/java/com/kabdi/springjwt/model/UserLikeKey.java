package com.kabdi.springjwt.model;

import java.io.Serializable;

import javax.persistence.Column;

public class UserLikeKey implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1368440828076359564L;

	@Column(name = "likerId", nullable=false)
	private int likerId;

	@Column(name = "likeeId", nullable=false)
	private int likeeId;

	public UserLikeKey() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getLikerId() {
		return likerId;
	}

	public void setLikerId(int likerId) {
		this.likerId = likerId;
	}

	public int getLikeeId() {
		return likeeId;
	}

	public void setLikeeId(int likeeId) {
		this.likeeId = likeeId;
	}

}
