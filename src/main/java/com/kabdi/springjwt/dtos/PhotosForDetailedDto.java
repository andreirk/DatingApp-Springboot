package com.kabdi.springjwt.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PhotosForDetailedDto {
	
	private int id;

	private String url;
	
	private String description;
	
	private Date dateAdded;
	
	@JsonProperty("isMain")
	private boolean isMain;

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

}
