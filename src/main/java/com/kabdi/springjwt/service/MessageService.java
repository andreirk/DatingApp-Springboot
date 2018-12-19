package com.kabdi.springjwt.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kabdi.springjwt.dtos.MessageForCreationDto;
import com.kabdi.springjwt.dtos.MessageToReturnDto;
import com.kabdi.springjwt.model.Message;
import com.kabdi.springjwt.model.User;
import com.kabdi.springjwt.payload.ApiResponse;
import com.kabdi.springjwt.payload.PaginationResult;

@Service
@Transactional
public class MessageService extends DatingService implements IMessageService {



	@Override
	public ApiResponse<?> createMessage(int userId, MessageForCreationDto messageForCreationDto, HttpServletRequest request) {

		if(!userRepository.existsById(userId)){
			return new ApiResponse<>("User not found", HttpStatus.NOT_FOUND);
		}

		int currentUserId = jwtTokenProvider.getUserIdFromJWT(jwtAuthenticationFilter.getJwtFromRequest(request));
		if (currentUserId != userId) {
			return new ApiResponse<>("User not authorized", HttpStatus.UNAUTHORIZED);
		}
		
		if(!userRepository.existsById(messageForCreationDto.getRecipientId())){
			return new ApiResponse<>("Could not find recipient user", HttpStatus.NOT_FOUND);
		}
		
		User sender = userRepository.findOneWithMainPhoto(userId);
		User recipient = userRepository.findOneWithMainPhoto(messageForCreationDto.getRecipientId());
		messageForCreationDto.setSenderId(userId);	
		
		Message message = dozerBeanMapper.map(messageForCreationDto, Message.class);
		messageRepository.save(message);
		MessageToReturnDto messageToReturnDto = messageMapper.mapMessageForCreationDtoToMessageToReturnDto(message, sender, recipient);

		return new ApiResponse<>(messageToReturnDto, HttpStatus.OK);

	}
	
	@Override
	public ApiResponse<?> getMessage(int userId, int messageId, HttpServletRequest request) {
		
		if(!userRepository.existsById(userId)){
			return new ApiResponse<>("User not found", HttpStatus.NOT_FOUND);
		}

		int currentUserId = jwtTokenProvider.getUserIdFromJWT(jwtAuthenticationFilter.getJwtFromRequest(request));
		if (currentUserId != userId) {
			return new ApiResponse<>("User not authorized", HttpStatus.UNAUTHORIZED);
		}

		Optional<Message> messageFromRepo = messageRepository.findById(messageId);

		if (messageFromRepo.get() == null) {
			return new ApiResponse<>("Message not found", HttpStatus.NOT_FOUND);
		}
		
		return new ApiResponse<>(messageFromRepo.get(), HttpStatus.OK);
	}

	@Override
	public ApiResponse<?> getMessagesForUser(int userId, String messageContainer, Pageable pageableRequest, HttpServletRequest request) {

		Boolean isRecipient = true;
		
		User user = userRepository.findOneWithMainPhoto(userId);
		if(user == null){
			return new ApiResponse<>("User not found", HttpStatus.NOT_FOUND);
		}

		int currentUserId = jwtTokenProvider.getUserIdFromJWT(jwtAuthenticationFilter.getJwtFromRequest(request));
		if (currentUserId != userId) {
			return new ApiResponse<>("User not authorized", HttpStatus.UNAUTHORIZED);
		}
		
		Page<Message> messagesEntities = null;
	
		switch (messageContainer) {
		case "Inbox":
			messagesEntities = messageRepository.findMessagesInForUser(userId, pageableRequest);
			break;
		case "Outbox":
			messagesEntities = messageRepository.findMessagesOutForUser(userId, pageableRequest);
			isRecipient = false;
			break;
		default:
			messagesEntities = messageRepository.findMessagesDefaultForUser(userId, pageableRequest);
			break;
		}

		PaginationResult paginationResult = new PaginationResult(messagesEntities.getNumber(),
				messagesEntities.getSize(), (int) messagesEntities.getTotalElements(),
				messagesEntities.getTotalPages());
		List<Message> messages = messagesEntities.getContent();
		List<MessageToReturnDto> messagesListDto = messageMapper.mapListMessageToMessageToReturnDto(messages, user, isRecipient);
/*		List<MessageToReturnDto> messagesListDto = customListConverter.map(dozerBeanMapper, messages,
				MessageToReturnDto.class);*/
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Pagination", paginationResult.toJson());
		
		return new ApiResponse<>(HttpStatus.OK, responseHeaders, messagesListDto);
	}

	@Override
	public ApiResponse<?> getMessageThread(int userId, int recipientId, HttpServletRequest request) {
		
		if(!userRepository.existsById(userId)){
			return new ApiResponse<>("User not found", HttpStatus.NOT_FOUND);
		}

		int currentUserId = jwtTokenProvider.getUserIdFromJWT(jwtAuthenticationFilter.getJwtFromRequest(request));
		if (currentUserId != userId) {
			return new ApiResponse<>("User not authorized", HttpStatus.UNAUTHORIZED);
		}
		
		User sender = userRepository.findOneWithMainPhoto(userId);
		User recipient = userRepository.findOneWithMainPhoto(recipientId);
		
		List<Message> messages = messageRepository.getMessageThread(userId, recipientId);
		List<MessageToReturnDto> messagesListDto = messageMapper.mapListMessageToMessageToReturnDto(messages, sender, recipient);

		return new ApiResponse<>(messagesListDto, HttpStatus.OK);
	}

	@Override
	public ApiResponse<?> deleteMessage(int userId, int messageId, HttpServletRequest request) {
		
		if(!userRepository.existsById(userId)){
			return new ApiResponse<>("User not found", HttpStatus.NOT_FOUND);
		}

		int currentUserId = jwtTokenProvider.getUserIdFromJWT(jwtAuthenticationFilter.getJwtFromRequest(request));
		if (currentUserId != userId) {
			return new ApiResponse<>("User not authorized", HttpStatus.UNAUTHORIZED);
		}
		
		Optional<Message> messageFromRepo = messageRepository.findById(messageId);
		if (!messageFromRepo.isPresent()) {
			return new ApiResponse<>("Message not found", HttpStatus.NOT_FOUND);
		}
		
		if (messageFromRepo.get().getSender().getId() == userId) {
			messageFromRepo.get().setSenderDeleted(true);
			messageRepository.save(messageFromRepo.get());
		}

		if (messageFromRepo.get().getRecipient().getId() == userId) {
			messageFromRepo.get().setRecipientDeleted(true);
			messageRepository.save(messageFromRepo.get());
		}

		if (messageFromRepo.get().isRecipientDeleted() && messageFromRepo.get().isSenderDeleted()) {
			messageRepository.delete(messageFromRepo.get());
		}
		
		return new ApiResponse<>("Message succesfully deleted", HttpStatus.NO_CONTENT);

	}

	@Override
	public ApiResponse<?> markMessageAsRead(int userId, int messageId, HttpServletRequest request) {
		
		ApiResponse<?> apiResponse = validateUser(userId, request);
		if(apiResponse != null){
			return apiResponse;
		}
		
		Optional<Message> messageFromRepo = messageRepository.findById(messageId);

		if (messageFromRepo.get().getRecipient().getId() != userId) {
			return new ApiResponse<>("User not authorized", HttpStatus.UNAUTHORIZED);
		}

		messageFromRepo.get().setRead(true);
		messageFromRepo.get().setDateRead(new Date());
		messageRepository.save(messageFromRepo.get());
		
		return new ApiResponse<>(HttpStatus.NO_CONTENT);
	}
	
	


	
	

}
