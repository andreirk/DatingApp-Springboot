package com.kabdi.springjwt.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Pageable;

import com.kabdi.springjwt.dtos.MessageForCreationDto;
import com.kabdi.springjwt.payload.ApiResponse;


public interface IMessageService {
	
	ApiResponse createMessage(int userId, MessageForCreationDto messageForCreationDto, HttpServletRequest request);
	
	ApiResponse getMessage(int userId, int messageId, HttpServletRequest request);
	
	ApiResponse getMessagesForUser(int userId, String messageContainer, Pageable pageableRequest, HttpServletRequest request);

	ApiResponse getMessageThread(int userId, int recipientId, HttpServletRequest request);
	
	ApiResponse deleteMessage(int userId, int messageId, HttpServletRequest request);
	
	ApiResponse markMessageAsRead(int userId, int messageId, HttpServletRequest request);
}
