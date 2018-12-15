package com.kabdi.springjwt.dtos;


import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;



public class PhotoForReturnDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1787719931220862923L;
	
	private int id;

	private String url;
	
	private String description;
	
	private Date dateAdded;
	
	@JsonProperty("isMain")
	private boolean isMain;
	
	private String publicId;
	

	public PhotoForReturnDto() {
		super();
		this.dateAdded = new Date();
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

	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}

	public boolean isMain() {
		return isMain;
	}

	public void setMain(boolean isMain) {
		this.isMain = isMain;
	}


	
	

}
