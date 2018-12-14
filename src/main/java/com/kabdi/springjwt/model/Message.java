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
@Table(name = "messages")
public class Message {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="senderId")
    private User sender;
    
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="recipientId")
    private User recipient;
    
    private String content;
    
    private boolean isRead;
    
    private Date dateRead;
    
    private Date messageSent;
    
    private boolean senderDeleted;
    
    private boolean recipientDeleted;

	public Message() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public Date getDateRead() {
		return dateRead;
	}

	public void setDateRead(Date dateRead) {
		this.dateRead = dateRead;
	}

	public Date getMessageSent() {
		return messageSent;
	}

	public void setMessageSent(Date messageSent) {
		this.messageSent = messageSent;
	}

	public boolean isSenderDeleted() {
		return senderDeleted;
	}

	public void setSenderDeleted(boolean senderDeleted) {
		this.senderDeleted = senderDeleted;
	}

	public boolean isRecipientDeleted() {
		return recipientDeleted;
	}

	public void setRecipientDeleted(boolean recipientDeleted) {
		this.recipientDeleted = recipientDeleted;
	}

}
