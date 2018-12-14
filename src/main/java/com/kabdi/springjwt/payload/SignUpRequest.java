package com.kabdi.springjwt.payload;

import java.util.Date;

/**
 * Created by Khalid Abdi
 */

public class SignUpRequest {
	 private String gender;
	    
	    private Date dateOfBirth;
	    
	    private String knownAs;
	    
	    private Date created;
	    
	    private Date lastActive;
	    
	    private String city;
	    
	    private String country;

	    private String username;

	    private String password;

		public SignUpRequest() {
			super();
			this.created = new Date();
			this.lastActive = new Date();
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public Date getDateOfBirth() {
			return dateOfBirth;
		}

		public void setDateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
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
