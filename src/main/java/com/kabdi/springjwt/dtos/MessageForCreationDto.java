package com.kabdi.springjwt.dtos;

import java.util.Date;

public class MessageForCreationDto {
	
	private int senderId;
	
	private int recipientId;
	
	private Date messageSent;
	
	private String content;
	

	public MessageForCreationDto() {
		super();
		this.messageSent = new Date();
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}

	public Date getMessageSent() {
		return messageSent;
	}

	public void setMessageSent(Date messageSent) {
		this.messageSent = messageSent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	

}
