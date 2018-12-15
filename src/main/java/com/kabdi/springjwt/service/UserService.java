package com.kabdi.springjwt.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import com.kabdi.springjwt.dtos.UserForDetailedDto;
import com.kabdi.springjwt.dtos.UserForListDto;
import com.kabdi.springjwt.dtos.UserForUpdateDto;
import com.kabdi.springjwt.model.Like;
import com.kabdi.springjwt.model.User;
import com.kabdi.springjwt.payload.ApiResponse;
import com.kabdi.springjwt.payload.PaginationResult;

@Service
@Transactional
public class UserService extends DatingService implements IUserService {

	
	@Override
	public ApiResponse getUsers(Map<String, Object> params, HttpServletRequest request) {
		
		int currentUserId = jwtTokenProvider.getUserIdFromJWT(jwtAuthenticationFilter.getJwtFromRequest(request));
		Optional<User> user = userRepository.findById(currentUserId);
		String gender = "";
		int pageNumber = 0;
		
		if((Integer) params.get("pageNumber") != 0) {
			pageNumber = (Integer) params.get("pageNumber") - 1;
		}
		
		Pageable pageableRequest = PageRequest.of(pageNumber, (Integer) params.get("pageSize"));
		Page<User> usersEntities = null;
		
		if (StringUtils.isEmpty((String) params.get("gender"))) {
			if(StringUtils.equals(user.get().getGender(), "male")){
				gender = "female";
			} else gender = "male";
		} else gender = (String) params.get("gender");

		if (StringUtils.equals((String) params.get("Likers"), "true")) {
			usersEntities = userRepository.findUsersLikers(currentUserId, pageableRequest);
		} else if (StringUtils.equals((String) params.get("Likees"), "true")) {
			usersEntities = userRepository.findUsersLikees(currentUserId, pageableRequest);
		} else {
			if (!((Integer) params.get("minAge") >= 18 && (Integer) params.get("maxAge") <= 99)) {
				return new ApiResponse("Minimum age and Maximum age should be between 18 and 99!", HttpStatus.BAD_REQUEST);
			}
			if (StringUtils.equals((String) params.get("orderBy"), "created")) {
				usersEntities = userRepository.findUsersCreated(gender, getMinDobDate((Integer) params.get("maxAge")), getMaxDobDate((Integer) params.get("minAge")),
						pageableRequest);
			} else {
				usersEntities = userRepository.findUsersLastActive(gender, getMinDobDate((Integer) params.get("maxAge")), getMaxDobDate((Integer) params.get("minAge")),
						pageableRequest);
			}
		}

		PaginationResult paginationResult = new PaginationResult(pageNumber, usersEntities.getSize(),
				(int) usersEntities.getTotalElements(), usersEntities.getTotalPages());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Pagination", paginationResult.toJson());
		List<User> users = usersEntities.getContent();
		List<UserForListDto> userForListDto = userMapper.mapListUserToUserForListDto(users);
		//return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).body(userForListDto);
		return new ApiResponse(HttpStatus.OK, responseHeaders, userForListDto);
	}


	@Override
	public ApiResponse getUser(int userId, HttpServletRequest request) {

		if(!userRepository.existsById(userId)){
			return new ApiResponse("User not found", HttpStatus.NOT_FOUND);
		}
		
		Optional<User> user = userRepository.findOneWithPhotos(userId);

		//UserForDetailedDto userForDetailedDto = dozerBeanMapper.map(user.get(), UserForDetailedDto.class);
		UserForDetailedDto userForDetailedDto = userMapper.mapUserToUserForDetailDto(user.get());
		//return ResponseEntity.ok(userForDetailedDto);
		return new ApiResponse(userForDetailedDto, HttpStatus.OK);
	}

	@Override
	public ApiResponse updateUser(int userId, UserForUpdateDto userForUpdateDto, HttpServletRequest request) {

		int currentUserId = jwtTokenProvider.getUserIdFromJWT(jwtAuthenticationFilter.getJwtFromRequest(request));
		if (currentUserId != userId) {
			return new ApiResponse("User not authorized", HttpStatus.UNAUTHORIZED);
		}
		Optional<User> user = userRepository.findById(userId);

		if (!user.isPresent()) {
			return new ApiResponse("User not found", HttpStatus.NOT_FOUND);
		}

		User userFromRepo = user.get();
		userForUpdateDto.setId(userId);
		userFromRepo.setCity(userForUpdateDto.getCity());
		userFromRepo.setCountry(userForUpdateDto.getCountry());
		userFromRepo.setInterests(userForUpdateDto.getInterests());
		userFromRepo.setIntroduction(userForUpdateDto.getIntroduction());
		userFromRepo.setLookingFor(userForUpdateDto.getLookingFor());
		userRepository.save(userFromRepo);

		return new ApiResponse("User succesfully updated", HttpStatus.NO_CONTENT);
	}


	@Override
	public ApiResponse likeUser(int userId, int recipientId, HttpServletRequest request) {
		
		int currentUserId = jwtTokenProvider.getUserIdFromJWT(jwtAuthenticationFilter.getJwtFromRequest(request));
		if (currentUserId != userId) {
			return new ApiResponse("User not authorized", HttpStatus.UNAUTHORIZED);
		}
		Optional<User> liker = userRepository.findById(userId);

		if (!liker.isPresent()) {
			return new ApiResponse("User not found", HttpStatus.NOT_FOUND);
		}

		Optional<User> likee = userRepository.findById(recipientId);
		
		Optional<Like> like = likeRepository.findUserLike(userId, recipientId);
		
		if (like.isPresent()) {
			return new ApiResponse("You already liked this user", HttpStatus.BAD_REQUEST);
		}
		
		
		Like newLike = likeRepository.save(new Like(liker.get(), likee.get()));
		
		return new ApiResponse(HttpStatus.OK);
	}
	
}
