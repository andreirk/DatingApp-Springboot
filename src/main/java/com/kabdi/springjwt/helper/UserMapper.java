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
		UserForDetailedDto userForDetailedDto = new UserForDetailedDto();

		userForDetailedDto.setId(user.getId());
		userForDetailedDto.setUsername(user.getUsername());
		userForDetailedDto.setGender(user.getGender());
		userForDetailedDto.setAge(calculateAge(user.getDateOfBirth()));
		userForDetailedDto.setKnownAs(user.getKnownAs());
		userForDetailedDto.setCreated(user.getCreated());
		userForDetailedDto.setLastActive(user.getLastActive());
		userForDetailedDto.setCity(user.getCity());
		userForDetailedDto.setCountry(user.getCountry());
		userForDetailedDto.setIntroduction(user.getIntroduction());
		userForDetailedDto.setLookingFor(user.getLookingFor());
		userForDetailedDto.setInterests(user.getInterests());
		userForDetailedDto.setPhotoUrl(getMainPhoto(user));
		userForDetailedDto.setPhotos(photoMapper.mapListPhotoToPhotosForDetailedDto(user.getPhotos()));

		return userForDetailedDto;
	}

	public UserForListDto mapUserToUserForListDto(User user) {
		UserForListDto userForListDto = new UserForListDto();

		userForListDto.setId(user.getId());
		userForListDto.setUsername(user.getUsername());
		userForListDto.setGender(user.getGender());
		userForListDto.setAge(calculateAge(user.getDateOfBirth()));
		userForListDto.setKnownAs(user.getKnownAs());
		userForListDto.setCreated(user.getCreated());
		userForListDto.setLastActive(user.getLastActive());
		userForListDto.setCity(user.getCity());
		userForListDto.setCountry(user.getCountry());
		userForListDto.setPhotoUrl(getMainPhoto(user));

		return userForListDto;
	}

	public List<UserForListDto> mapListUserToUserForListDto(List<User> users) {

		List<UserForListDto> userForListDtoList = new ArrayList<UserForListDto>();

		for (User user : users) {
			userForListDtoList.add(mapUserToUserForListDto(user));
		}
		return userForListDtoList;
	}

	/*public String getMainPhoto(User user) {
		if (user != null) {
			Collection<Photo> photos = user.getPhotos();
			if(photos != null) {
				for (Photo photo : photos) {
					if (photo.isMain()) {
						return photo.getUrl();
					}
				}
			}
		}
		return null;
	}

	public Integer calculateAge(Date dateOfBirth) {

		if (dateOfBirth instanceof Date) {
			LocalDate birthDate = (dateOfBirth).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate now = LocalDate.now();
			return Period.between(birthDate, now).getYears();
		}
		return null;
	}
*/
}
