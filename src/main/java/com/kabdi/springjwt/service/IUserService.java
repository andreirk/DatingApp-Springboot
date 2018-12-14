package com.kabdi.springjwt.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kabdi.springjwt.dtos.UserForUpdateDto;
import com.kabdi.springjwt.payload.ApiResponse;


public interface IUserService {
	
	ApiResponse getUsers(Map<String, Object> params, HttpServletRequest request);
	
	ApiResponse getUser(int userId, HttpServletRequest request);
	
	ApiResponse updateUser(int userId, UserForUpdateDto userForUpdateDto, HttpServletRequest request);
	
	ApiResponse likeUser(int id, int recipientId, HttpServletRequest request);

}
