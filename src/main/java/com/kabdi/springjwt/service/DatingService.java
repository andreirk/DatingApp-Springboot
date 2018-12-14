package com.kabdi.springjwt.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kabdi.springjwt.helper.CustomListConverter;
import com.kabdi.springjwt.helper.UserMapper;
import com.kabdi.springjwt.payload.ApiResponse;
import com.kabdi.springjwt.repository.LikeRepository;
import com.kabdi.springjwt.repository.MessageRepository;
import com.kabdi.springjwt.repository.PhotoRepository;
import com.kabdi.springjwt.repository.UserRepository;
import com.kabdi.springjwt.security.JwtAuthenticationFilter;
import com.kabdi.springjwt.security.JwtTokenProvider;

@Service
public class DatingService {
	
	@Autowired
	MessageRepository messageRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PhotoRepository photoRepository;
	
	@Autowired
	LikeRepository likeRepository;
	
	@Autowired
	UserMapper userMapper;

	@Autowired
	DozerBeanMapper dozerBeanMapper;

	@Autowired
	CustomListConverter customListConverter;
	
	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	public DatingService() {
		super();
	}
	
	public Date getMinDobDate(int maxAge) {
		return Date.from(LocalDate.now().minusYears(maxAge + 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public Date getMaxDobDate(int minAge) {
		return Date.from(LocalDate.now().minusYears(minAge + 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public ApiResponse<?> validateUser(int userId, HttpServletRequest request){
		if(!userRepository.existsById(userId)){
			return new ApiResponse<>("User not found", HttpStatus.NOT_FOUND);
		}

		int currentUserId = jwtTokenProvider.getUserIdFromJWT(jwtAuthenticationFilter.getJwtFromRequest(request));
		if (currentUserId != userId) {
			return new ApiResponse<>("User not authorized", HttpStatus.UNAUTHORIZED);
		}
		return null;
	}
	
}
