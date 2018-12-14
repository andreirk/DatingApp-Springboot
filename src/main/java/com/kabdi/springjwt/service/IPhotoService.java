package com.kabdi.springjwt.service;

import javax.servlet.http.HttpServletRequest;


import org.springframework.web.multipart.MultipartFile;

import com.kabdi.springjwt.payload.ApiResponse;

public interface IPhotoService {
	
	ApiResponse getPhoto(int userId, int photoId, HttpServletRequest request);
	
	ApiResponse addPhotoForUser(MultipartFile file, int userId, HttpServletRequest request);
	
	ApiResponse setMainPhoto(int userId, int photoId, HttpServletRequest request);
	
	ApiResponse deletePhoto(int userId, int photoId, HttpServletRequest request);

}
