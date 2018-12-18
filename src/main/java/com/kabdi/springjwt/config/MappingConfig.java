package com.kabdi.springjwt.config;


import org.dozer.DozerBeanMapper;

import static org.dozer.loader.api.FieldsMappingOptions.customConverter;



import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kabdi.springjwt.dtos.MessageForCreationDto;
import com.kabdi.springjwt.dtos.MessageToReturnDto;
import com.kabdi.springjwt.dtos.PhotoForReturnDto;
import com.kabdi.springjwt.dtos.UserForDetailedDto;
import com.kabdi.springjwt.dtos.UserForListDto;
import com.kabdi.springjwt.dtos.UserForLoginDto;
import com.kabdi.springjwt.dtos.UserForUpdateDto;
import com.kabdi.springjwt.helper.CustomAgeConverter;
import com.kabdi.springjwt.helper.PhotoConverter;
import com.kabdi.springjwt.model.Message;
import com.kabdi.springjwt.model.Photo;
import com.kabdi.springjwt.model.User;

@Configuration
public class MappingConfig {
	
	@Bean
    public BeanMappingBuilder beanMappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
            	mapping(User.class, UserForListDto.class)
            	.fields("photos", "photoUrl", customConverter(PhotoConverter.class))
            	.fields("dateOfBirth", "age", customConverter(CustomAgeConverter.class));
                mapping(User.class, UserForDetailedDto.class).fields("dateOfBirth", "age", customConverter(CustomAgeConverter.class))
                .fields("photos", "photoUrl", customConverter(PhotoConverter.class))
                .exclude("likees").exclude("likers").exclude("roles");
                mapping(UserForUpdateDto.class, User.class).exclude("username").exclude("gender").exclude("dateOfBirth").exclude("knownAs").exclude("created")
                .exclude("lastActive").exclude("password").exclude("photos").exclude("messagesSent").exclude("messagesReceived").exclude("likers")
                .exclude("likees").exclude("roles");
                mapping(Photo.class, PhotoForReturnDto.class);
                mapping(User.class, UserForLoginDto.class);
                mapping(Message.class, MessageToReturnDto.class).fields("sender.id", "senderId")
                .fields("recipient.id", "recipientId").fields("sender.knownAs", "senderKnownAs").fields("recipient.knownAs", "recipientKnownAs");
                mapping(Message.class, MessageForCreationDto.class).fields("sender.id", "senderId")
                .fields("recipient.id", "recipientId");
            }
        };
    }

    @Bean
    public DozerBeanMapper beanMapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.addMapping(beanMappingBuilder());
        return dozerBeanMapper;
    }

    }
