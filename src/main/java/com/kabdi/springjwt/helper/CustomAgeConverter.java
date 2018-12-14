package com.kabdi.springjwt.helper;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.dozer.CustomConverter;


public class CustomAgeConverter implements CustomConverter {

	public Object convert(Object dest, Object source, Class<?> arg2, Class<?> arg3) {
        if (source == null) 
            return null;
         
        if (source instanceof Date) {
        	LocalDate birthDate = ((Date) source).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        	LocalDate now = LocalDate.now();
        	return Period.between(birthDate, now).getYears();
 
        } else if (source instanceof Integer) {
           return new Date();
        }
        return null;
    }

}
