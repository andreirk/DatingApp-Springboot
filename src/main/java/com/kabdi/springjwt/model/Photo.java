package com.kabdi.springjwt.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "photos")
public class Photo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String url;
    
    private String description;
    
    private Date dateAdded;
    
    private boolean isMain;
    
    private String publicId;
    
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
    private User user;
    
    public Photo() {
		super();
	}
    

	public Photo(String url, Date dateAdded, String publicId, User user) {
		super();
		this.url = url;
		this.dateAdded = dateAdded;
		this.publicId = publicId;
		this.user = user;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public boolean isMain() {
		return isMain;
	}

	public void setMain(boolean isMain) {
		this.isMain = isMain;
	}

	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
