package com.kabdi.springjwt.helper;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.kabdi.springjwt.dtos.UserForDetailedDto;
import com.kabdi.springjwt.dtos.UserForListDto;
import com.kabdi.springjwt.model.User;

@Component
public class UserMapper extends DatingMapper {

	@Autowired
	PhotoMapper photoMapper;

	public UserForDetailedDto mapUserToUserForDetailDto(User user) {
		
		UserForDetailedDto userForDetailedDto = dozerBeanMapper.map(user, UserForDetailedDto.class);
		userForDetailedDto.setAge(calculateAge(user.getDateOfBirth()));
		userForDetailedDto.setPhotoUrl(getMainPhotoUrl(user));
		userForDetailedDto.setPhotos(photoMapper.mapListPhotoToPhotosForDetailedDto(user.getPhotos()));
		
		return userForDetailedDto;
	}

	public UserForListDto mapUserToUserForListDto(User user) {
		
		UserForListDto userForListDto = dozerBeanMapper.map(user, UserForListDto.class);
		userForListDto.setAge(calculateAge(user.getDateOfBirth()));
		userForListDto.setPhotoUrl(getMainPhotoUrl(user));

		return userForListDto;
	}

	public List<UserForListDto> mapListUserToUserForListDto(List<User> users) {

		List<UserForListDto> userForListDtoList = new ArrayList<UserForListDto>();

		for (User user : users) {
			userForListDtoList.add(mapUserToUserForListDto(user));
		}
		return userForListDtoList;
	}

}
