package com.kabdi.springjwt.helper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CustomListConverter {
	
	public  <T, U> List<U> map(final Mapper mapper, final List<T> source, final Class<U> destType) {

	    final List<U> dest = new ArrayList<>();

	    for (T element : source) {
	        dest.add(mapper.map(element, destType));
	    }

	    return dest;
	}
	
	/*public  <T, U> Page<U> map(final Mapper mapper, final Page<T> source, final Class<U> destType) {

	    final List<U> dest = new ArrayList<>();

	    for (T element : source) {
	        dest.add(mapper.map(element, destType));
	    }

	    return (Page<U>) dest;
	}
*/
}
