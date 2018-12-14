package com.kabdi.springjwt.controller;


import java.io.IOException;


import javax.servlet.http.HttpServletRequest;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.kabdi.springjwt.service.IPhotoService;
import com.kabdi.springjwt.payload.ApiResponse;


@RestController
@RequestMapping("/api/users/{userId}/photos")
public class PhotosController {

	@Autowired
	IPhotoService photoService;

	@GetMapping("{photoId}")
	public ResponseEntity<?> getPhoto(@PathVariable int userId, @PathVariable int photoId, HttpServletRequest request) {
			
		ApiResponse<?> apiResponse = photoService.getPhoto(userId, photoId, request);	
		return new ResponseEntity<>(apiResponse.getResponseBody(), apiResponse.getHttpStatus());
	}

	@PostMapping
	public ResponseEntity<?> AddPhotoForUser(
			@RequestParam("file") MultipartFile file, 
			@PathVariable int userId,
			HttpServletRequest request) throws IOException {
		
		ApiResponse<?> apiResponse = photoService.addPhotoForUser(file, userId, request);		
		return new ResponseEntity<>(apiResponse.getResponseBody(), apiResponse.getHttpStatus());
	}

	@PostMapping("{photoId}/setMain")
	public ResponseEntity<?> setMainPhoto(@PathVariable int userId, @PathVariable int photoId, HttpServletRequest request) {
		
		ApiResponse<?> apiResponse = photoService.setMainPhoto(userId, photoId, request);		
		return new ResponseEntity<>(apiResponse.getResponseBody(), apiResponse.getHttpStatus());
		
	}

	@DeleteMapping("{photoId}")
	public ResponseEntity<?> deletePhoto(@PathVariable int userId, @PathVariable int photoId, HttpServletRequest request) {
			
		ApiResponse<?> apiResponse = photoService.deletePhoto(userId, photoId, request);
		return ResponseEntity.status(apiResponse.getHttpStatus()).body(apiResponse.getMessage());
	}

}
