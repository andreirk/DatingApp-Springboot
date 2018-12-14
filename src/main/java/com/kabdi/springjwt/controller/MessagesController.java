package com.kabdi.springjwt.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kabdi.springjwt.dtos.MessageForCreationDto;
import com.kabdi.springjwt.payload.ApiResponse;
import com.kabdi.springjwt.service.IMessageService;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("api/users/{userId}/messages")
public class MessagesController {
	
	@Autowired
	IMessageService messageService;
	
	
	@GetMapping("{id}")
	public ResponseEntity<?> getMessage(@PathVariable int userId, @PathVariable int id, HttpServletRequest request) {

		ApiResponse<?> apiResponse = messageService.getMessage(userId, id, request);
		
		return new ResponseEntity<>(apiResponse.getResponseBody(), apiResponse.getHttpStatus());
	}
	
	@GetMapping
	public ResponseEntity<?> getMessagesForUser(@PathVariable int userId,
			@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name = "messageContainer", defaultValue = "Unread") String messageContainer,
			HttpServletRequest request) {

		
		if(pageNumber != 0) {
			pageNumber = pageNumber - 1;
		}
		
		Pageable pageableRequest = PageRequest.of(pageNumber, pageSize);
		
		ApiResponse<?> apiResponse = messageService.getMessagesForUser(userId, messageContainer, pageableRequest, request);
		
		return new ResponseEntity<>(apiResponse.getResponseBody(), apiResponse.getResponseHeaders(), apiResponse.getHttpStatus());
	}

	
	@GetMapping("thread/{recipientId}")
	public ResponseEntity<?> getMessageThread(
			@PathVariable int userId, 
			@PathVariable int recipientId, 
			HttpServletRequest request) {
	
		ApiResponse<?> apiResponse = messageService.getMessageThread(userId, recipientId, request);
		
		return new ResponseEntity<>(apiResponse.getResponseBody(), apiResponse.getHttpStatus());
	}

	
	@PostMapping
	public ResponseEntity<?> createMessage(
			@PathVariable int userId, 
			@RequestBody MessageForCreationDto messageForCreationDto,
			HttpServletRequest request)  {	
		
		ApiResponse<?> apiResponse = messageService.createMessage(userId, messageForCreationDto, request);

		return new ResponseEntity<>(apiResponse.getResponseBody(), apiResponse.getHttpStatus());
	}
	
	@PostMapping("{id}")
	public ResponseEntity<?> deleteMessage(
			@PathVariable int userId, 
			@PathVariable int id, 
			HttpServletRequest request)  {	
		
		ApiResponse<?> apiResponse = messageService.deleteMessage(userId, id, request);
		
		return new ResponseEntity<>(apiResponse.getResponseBody(), apiResponse.getHttpStatus());
	}
	
	
	@PostMapping("{id}/read")
	public ResponseEntity<?> markMessageAsRead(
			@PathVariable int userId, 
			@PathVariable int id, 
			HttpServletRequest request)  {	
		
		ApiResponse<?> apiResponse = messageService.markMessageAsRead(userId, id, request);
		
		return new ResponseEntity<String>(apiResponse.getHttpStatus());
	}
}
