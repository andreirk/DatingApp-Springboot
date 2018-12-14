package com.kabdi.springjwt.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "likes")
public class Like implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1621493789998995184L;
	
/*	@EmbeddedId
	private UserLikeKey key;*/
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@ManyToOne(optional=false)
//	@JoinColumn(name="likerId",insertable=false, updatable=false)
	private User liker;
	
	@ManyToOne(optional=false)
//	@JoinColumn(name="likeeId",insertable=false, updatable=false)
	private User likee;

	public Like() {
		super();
	}
	
	

	public Like(User liker, User likee) {
		super();
		this.liker = liker;
		this.likee = likee;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getLiker() {
		return liker;
	}

	public void setLiker(User liker) {
		this.liker = liker;
	}

	public User getLikee() {
		return likee;
	}

	public void setLikee(User likee) {
		this.likee = likee;
	}
	
	

}
