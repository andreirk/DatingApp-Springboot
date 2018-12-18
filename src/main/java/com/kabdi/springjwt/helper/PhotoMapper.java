package com.kabdi.springjwt.helper;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.kabdi.springjwt.dtos.PhotosForDetailedDto;
import com.kabdi.springjwt.model.Photo;

@Component
public class PhotoMapper {
	
	public PhotosForDetailedDto mapPhotoToPhotosForDetailedDto(Photo photo){
		
		PhotosForDetailedDto photosForDetailedDto = new PhotosForDetailedDto();
		photosForDetailedDto.setId(photo.getId());
		photosForDetailedDto.setUrl(photo.getUrl());
		photosForDetailedDto.setDescription(photo.getDescription());
		photosForDetailedDto.setDateAdded(photo.getDateAdded());
		photosForDetailedDto.setMain(photo.isMain());
		
		return photosForDetailedDto;
	}
	
	public Collection<PhotosForDetailedDto> mapListPhotoToPhotosForDetailedDto(Collection<Photo> photos){
		
		Collection<PhotosForDetailedDto> photosDtoList = new ArrayList<PhotosForDetailedDto>();
		
		for(Photo photo : photos){
			photosDtoList.add(mapPhotoToPhotosForDetailedDto(photo));
		}
		
		return photosDtoList;
	}
	
	

}
