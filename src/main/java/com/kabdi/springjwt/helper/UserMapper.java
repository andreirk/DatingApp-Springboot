package com.kabdi.springjwt.helper;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kabdi.springjwt.dtos.UserForDetailedDto;
import com.kabdi.springjwt.model.Photo;
import com.kabdi.springjwt.model.User;

@Service
public class UserMapper {
	
	@Autowired
	PhotoMapper photoMapper;
		
	
	public UserForDetailedDto mapUserToUserForDetailDto(User user){
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
	
	public String getMainPhoto(User user){
			if (user != null){
				Collection<Photo> photos = user.getPhotos();
				for(Photo photo : photos){
					if(photo.isMain()){
						return photo.getUrl();
					}
				}
			}
			return null;
	}
	
	public Integer calculateAge(Date dateOfBirth){
         
        if (dateOfBirth instanceof Date) {
        	LocalDate birthDate = (dateOfBirth).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        	LocalDate now = LocalDate.now();
        	return Period.between(birthDate, now).getYears();
        } 
        return null;
	}

}
