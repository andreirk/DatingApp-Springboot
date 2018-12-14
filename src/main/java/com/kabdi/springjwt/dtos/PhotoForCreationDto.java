package com.kabdi.springjwt.dtos;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class PhotoForCreationDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1787719931220862923L;

	private String url;
	
	private MultipartFile file;
	
	private String description;
	
	private Date dateAdded;
	
	private String publicId;
	

	public PhotoForCreationDto() {
		super();
		this.dateAdded = new Date();
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

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	

}
