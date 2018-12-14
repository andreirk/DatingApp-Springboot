package com.kabdi.springjwt.helper;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.dozer.CustomConverter;
import org.dozer.DozerConverter;

import com.kabdi.springjwt.model.Photo;


@SuppressWarnings("rawtypes")
public class PhotoConverter extends DozerConverter<Collection, String> {

	
	/*public PhotoConverter() {
        super(Boolean.class, String.class);
    }*/

	
	public PhotoConverter() {
		super(Collection.class, String.class);
		// TODO Auto-generated constructor stub
	}

	/*public Object convert(Object dest, Object source, Class<?> arg2, Class<?> arg3) {
        if (source == null) 
            return null;
         
        if (source instanceof Collection<?>) {
        	Collection<Photo> photos = (Collection<Photo>) source;
        	return photos.stream()
        	.filter(p -> p.isMain() == true)
            .findFirst()
            .map(Photo::getUrl);
 
        } else if (source instanceof Integer) {
           return new Date();
        }
        return null;
    }*/

	@Override
	public String convertTo(Collection source, String destination) {
		if (source != null){
			Collection<Photo> photos = (Collection<Photo>) source;
			for(Photo photo : photos){
				if(photo.isMain()){
					return photo.getUrl();
				}
			}
		}
		return null;
      /*  } else if (source != null){
        	Collection<Photo> photos = (Collection<Photo>) source;
	    	return photos.stream()
	    	.filter(p -> p.isMain() == true)
	        .findFirst()
	        .map(p -> p.getUrl)
	        .orElse(null);
        }*/
	}

	@Override
	public Collection<Photo> convertFrom(String source, Collection destination) {
		// TODO Auto-generated method stub
		return null;
	}

}
