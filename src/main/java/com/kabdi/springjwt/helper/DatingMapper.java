package com.kabdi.springjwt.helper;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.kabdi.springjwt.model.Photo;
import com.kabdi.springjwt.model.User;

public class DatingMapper {
	
	@Autowired
	DozerBeanMapper dozerBeanMapper;
	
	
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
	}*/
	
	public String getMainPhotoUrl(User user) {
		if(user != null) {
			Collection<Photo> photos = user.getPhotos();
			return photos.stream().filter(p -> p.isMain() == true).findFirst().map(Photo::getUrl).orElse(null);
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


}
