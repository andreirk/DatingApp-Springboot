package com.kabdi.springjwt.dtos;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import com.kabdi.springjwt.model.Photo;

public class UserForDetailedDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7233820365413236832L;

	private int id;
	
	private String username;

	private String gender;
	
	private Integer age;
	
	private String knownAs;
	
	private Date created;
	
	private Date lastActive;
	
	private String city;
    
	private String country;
	
	private String Introduction;
    
    private String lookingFor;
    
    private String interests;
	
	private String photoUrl;
	
	private Collection<PhotosForDetailedDto> photos;

	public UserForDetailedDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getKnownAs() {
		return knownAs;
	}

	public void setKnownAs(String knownAs) {
		this.knownAs = knownAs;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastActive() {
		return lastActive;
	}

	public void setLastActive(Date lastActive) {
		this.lastActive = lastActive;
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

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Collection<PhotosForDetailedDto> getPhotos() {
		return photos;
	}

	public void setPhotos(Collection<PhotosForDetailedDto> photos) {
		this.photos = photos;
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
}
