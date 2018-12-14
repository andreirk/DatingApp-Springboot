package com.kabdi.springjwt.dtos;

import java.util.Date;

public class MessageToReturnDto {
	
	private int id;
	
	private int senderId;
	
	private String senderKnownAs;
	
	private String senderPhotoUrl;
	
	private int recipientId;
	
	private String recipientKnownAs;
	
	private String recipientPhotoUrl;
	
	private String content;
	
	private boolean isRead;
	
	private Date dateRead;
	
	private Date messageSent;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public String getSenderKnownAs() {
		return senderKnownAs;
	}

	public void setSenderKnownAs(String senderKnownAs) {
		this.senderKnownAs = senderKnownAs;
	}

	public String getSenderPhotoUrl() {
		return senderPhotoUrl;
	}

	public void setSenderPhotoUrl(String senderPhotoUrl) {
		this.senderPhotoUrl = senderPhotoUrl;
	}

	public int getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}

	public String getRecipientKnownAs() {
		return recipientKnownAs;
	}

	public void setRecipientKnownAs(String recipientKnownAs) {
		this.recipientKnownAs = recipientKnownAs;
	}

	public String getRecipientPhotoUrl() {
		return recipientPhotoUrl;
	}

	public void setRecipientPhotoUrl(String recipientPhotoUrl) {
		this.recipientPhotoUrl = recipientPhotoUrl;
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

}
