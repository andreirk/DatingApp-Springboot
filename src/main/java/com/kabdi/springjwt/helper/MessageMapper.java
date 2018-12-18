package com.kabdi.springjwt.helper;

import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kabdi.springjwt.dtos.MessageToReturnDto;
import com.kabdi.springjwt.model.Message;
import com.kabdi.springjwt.model.User;

@Service
public class MessageMapper {
	
	@Autowired
	UserMapper userMapper;

	public MessageToReturnDto mapMessageToMessageToReturnDto(Message message, User user, Boolean isRecipient) {
		MessageToReturnDto messageToReturnDto = new MessageToReturnDto();

		if(isRecipient) {
			messageToReturnDto.setSenderId(message.getSender().getId());
			messageToReturnDto.setSenderKnownAs(message.getSender().getKnownAs());
			messageToReturnDto.setSenderPhotoUrl(userMapper.getMainPhoto(message.getSender()));
			messageToReturnDto.setRecipientId(user.getId());
			messageToReturnDto.setRecipientKnownAs(user.getKnownAs());
			messageToReturnDto.setRecipientPhotoUrl(userMapper.getMainPhoto(user));
		} else {
			messageToReturnDto.setSenderId(user.getId());
			messageToReturnDto.setSenderKnownAs(user.getKnownAs());
			messageToReturnDto.setSenderPhotoUrl(userMapper.getMainPhoto(user));
			messageToReturnDto.setRecipientId(message.getRecipient().getId());
			messageToReturnDto.setRecipientKnownAs(message.getRecipient().getKnownAs());
			messageToReturnDto.setRecipientPhotoUrl(userMapper.getMainPhoto(message.getRecipient()));
		}
		messageToReturnDto.setId(message.getId());
		messageToReturnDto.setContent(message.getContent());
		messageToReturnDto.setDateRead(message.getDateRead());
		messageToReturnDto.setMessageSent(message.getMessageSent());

		return messageToReturnDto;
	}

	
	public List<MessageToReturnDto> mapListMessageToMessageToReturnDto(List<Message> messages, User user, Boolean isRecipient) {

		List<MessageToReturnDto> messagesListDto = new ArrayList<MessageToReturnDto>();

		for (Message message : messages) {
			messagesListDto.add(mapMessageToMessageToReturnDto(message, user, isRecipient));
		}
		return messagesListDto;
	}

	

}
