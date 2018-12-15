package com.kabdi.springjwt.model;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jndanial.com (danial jalal nouri).
 * http://http://www.jndanial.com/54/
 */

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        })
})
public class User implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8653762802216117705L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    private String username;
    
    private String gender;
    
    private Date dateOfBirth;
    
    private String knownAs;
    
    private Date created;
    
    private Date lastActive;
    
    private String Introduction;
    
    private String lookingFor;
    
    private String interests;
    
    private String city;
    
    private String country;

    private String password;
    

	@OneToMany(mappedBy="user", fetch=FetchType.LAZY, orphanRemoval=true, cascade = {CascadeType.ALL})
    private Collection<Photo> photos;
    
  //  @OneToMany(mappedBy="user", fetch=FetchType.LAZY, cascade = {CascadeType.REMOVE})
    @OneToMany
    private Collection<Message> messagesSent;
    
   // @OneToMany(mappedBy="user", fetch=FetchType.LAZY, cascade = {CascadeType.REMOVE})
    @OneToMany
    private Collection<Message> messagesReceived;
    
    @OneToMany(mappedBy="liker", fetch=FetchType.LAZY)
    private Collection<Like> likers;
    
    @OneToMany(mappedBy="likee", fetch=FetchType.LAZY)
    private Collection<Like> likees;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

	public Collection<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Collection<Photo> photos) {
		this.photos = photos;
	}

	public Collection<Message> getMessagesSent() {
		return messagesSent;
	}

	public void setMessagesSent(Collection<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}

	public Collection<Message> getMessagesReceived() {
		return messagesReceived;
	}

	public void setMessagesReceived(Collection<Message> messagesReceived) {
		this.messagesReceived = messagesReceived;
	}

	public Collection<Like> getLikers() {
		return likers;
	}

	public void setLikers(Collection<Like> likers) {
		this.likers = likers;
	}

	public Collection<Like> getLikees() {
		return likees;
	}

	public void setLikees(Collection<Like> likees) {
		this.likees = likees;
	}
}