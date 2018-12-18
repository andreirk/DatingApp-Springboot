package com.kabdi.springjwt.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kabdi.springjwt.dtos.UserForUpdateDto;
import com.kabdi.springjwt.payload.ApiResponse;
import com.kabdi.springjwt.service.IUserService;

@RestController
@RequestMapping("/api/users")
public class UsersController {


	@Autowired
	IUserService userService;

	@GetMapping
	//@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getUsers(@RequestParam(name = "gender", required = false) String gender,
			@RequestParam(name = "minAge", defaultValue = "18") int minAge,
			@RequestParam(name = "maxAge", defaultValue = "99") int maxAge,
			@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(name = "Likers", required = false) String likers,
			@RequestParam(name = "Likees", required = false) String likees,
			@RequestParam(name = "orderBy", defaultValue = "lastActive") String orderBy,
			@RequestParam(name = "pageSize", defaultValue = "40") int pageSize, HttpServletRequest request) {

		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("gender", gender);
		params.put("minAge", minAge);
		params.put("maxAge", maxAge);
		params.put("pageNumber", pageNumber);
		params.put("pageSize", pageSize);
		params.put("Likers", likers);
		params.put("Likees", likees);
		params.put("orderBy", orderBy);
		
		ApiResponse<?> apiResponse = userService.getUsers(params, request);

		return new ResponseEntity<>(apiResponse.getResponseBody(), apiResponse.getResponseHeaders(), apiResponse.getHttpStatus());
		
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getUser(@PathVariable int id, HttpServletRequest request) {

		ApiResponse<?> apiResponse = userService.getUser(id, request);

		return new ResponseEntity<>(apiResponse.getResponseBody(), apiResponse.getHttpStatus());
	}

	@PutMapping("{id}")
	public ResponseEntity<?> udateUser(@PathVariable int id, @RequestBody UserForUpdateDto userForUpdateDto,
			HttpServletRequest request) {

		ApiResponse<?> apiResponse = userService.updateUser(id, userForUpdateDto, request);

		return new ResponseEntity<>(apiResponse.getResponseBody(), apiResponse.getHttpStatus());
	}
	
	@PostMapping("{id}/like/{recipientId}")
	public ResponseEntity<?> likeUser(@PathVariable int id, @PathVariable int recipientId,
			HttpServletRequest request) {

		ApiResponse<?> apiResponse = userService.likeUser(id, recipientId, request);

		return new ResponseEntity<>(apiResponse.getResponseBody(), apiResponse.getHttpStatus());
	}

	

}
