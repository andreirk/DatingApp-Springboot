package com.kabdi.springjwt.helper;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.kabdi.springjwt.dtos.MessageToReturnDto;
import com.kabdi.springjwt.model.Message;
import com.kabdi.springjwt.model.User;

@Component
public class MessageMapper extends DatingMapper {
	

	public MessageToReturnDto mapMessageToMessageToReturnDto(Message message, User user, Boolean isRecipient) {
		MessageToReturnDto messageToReturnDto = new MessageToReturnDto();

		if(isRecipient) {
			messageToReturnDto.setSenderId(message.getSender().getId());
			messageToReturnDto.setSenderKnownAs(message.getSender().getKnownAs());
			messageToReturnDto.setSenderPhotoUrl(getMainPhoto(message.getSender()));
			messageToReturnDto.setRecipientId(user.getId());
			messageToReturnDto.setRecipientKnownAs(user.getKnownAs());
			messageToReturnDto.setRecipientPhotoUrl(getMainPhoto(user));
		} else {
			messageToReturnDto.setSenderId(user.getId());
			messageToReturnDto.setSenderKnownAs(user.getKnownAs());
			messageToReturnDto.setSenderPhotoUrl(getMainPhoto(user));
			messageToReturnDto.setRecipientId(message.getRecipient().getId());
			messageToReturnDto.setRecipientKnownAs(message.getRecipient().getKnownAs());
			messageToReturnDto.setRecipientPhotoUrl(getMainPhoto(message.getRecipient()));
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
