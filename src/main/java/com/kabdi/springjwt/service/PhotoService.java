package com.kabdi.springjwt.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kabdi.springjwt.dtos.PhotoForReturnDto;
import com.kabdi.springjwt.model.Photo;
import com.kabdi.springjwt.model.User;
import com.kabdi.springjwt.payload.ApiResponse;

@Service
public class PhotoService extends DatingService implements IPhotoService{
	
	@Autowired
	CloudinaryService cloudinaryService;

	@Override
	public ApiResponse<?> getPhoto(int userId, int photoId, HttpServletRequest request) {
		
		if(!userRepository.existsById(userId)){
			return new ApiResponse<>("User not found", HttpStatus.NOT_FOUND);
		}

		int currentUserId = jwtTokenProvider.getUserIdFromJWT(jwtAuthenticationFilter.getJwtFromRequest(request));
		if (currentUserId != userId) {
			return new ApiResponse<>("User not authorized", HttpStatus.UNAUTHORIZED);
		}
		
		Optional<Photo> photo = photoRepository.findById(photoId);

		if (!photo.isPresent()) {
			return new ApiResponse<>("Photo not found", HttpStatus.NOT_FOUND);
		}

		PhotoForReturnDto photoForReturnDto = dozerBeanMapper.map(photo.get(), PhotoForReturnDto.class);

		return new ApiResponse<>(photoForReturnDto, HttpStatus.OK);
	}

	@Override
	public ApiResponse<?> addPhotoForUser(MultipartFile file, int userId, HttpServletRequest request) {
	
		if(!userRepository.existsById(userId)){
			return new ApiResponse<>("User not found", HttpStatus.NOT_FOUND);
		}

		int currentUserId = jwtTokenProvider.getUserIdFromJWT(jwtAuthenticationFilter.getJwtFromRequest(request));
		if (currentUserId != userId) {
			return new ApiResponse<>("User not authorized", HttpStatus.UNAUTHORIZED);
		}
		
		String url = "";
		String publicId = "";
		Map result = new HashMap();

		result = cloudinaryService.uploadPhoto(file);
		url = (String) result.get("url");
		publicId = (String) result.get("public_id");
		
		User user = userRepository.findOneWithPhotos(userId).get();
		Photo photo = new Photo(url, new Date(), publicId, user);

		if (user.getPhotos().isEmpty()) {
			photo.setMain(true);
		}

		user.getPhotos().add(photo);
		photoRepository.save(photo);
		PhotoForReturnDto photoForReturnDto = dozerBeanMapper.map(photo, PhotoForReturnDto.class);

		return new ApiResponse<>(photoForReturnDto, HttpStatus.OK);
	}

	@Override
	public ApiResponse<?> setMainPhoto(int userId, int photoId, HttpServletRequest request) {
		
		if(!userRepository.existsById(userId)){
			return new ApiResponse<>("User not found", HttpStatus.NOT_FOUND);
		}
		
		int currentUserId = jwtTokenProvider.getUserIdFromJWT(jwtAuthenticationFilter.getJwtFromRequest(request));
		if (currentUserId != userId) {
			return new ApiResponse<>("User not authorized", HttpStatus.UNAUTHORIZED);
		}

		Optional<User> userWithPhotos = userRepository.findOneWithPhotos(userId);

		if (!userWithPhotos.get().getPhotos().stream().anyMatch(p -> p.getId() == photoId)) {
			return new ApiResponse<>("User not authorized", HttpStatus.UNAUTHORIZED);
		}

		Optional<Photo> photo = photoRepository.findById(photoId);

		if (!photo.isPresent()) {
			return new ApiResponse<>("Photo not found", HttpStatus.NOT_FOUND);
		}

		if (photo.get().isMain()) {
			return new ApiResponse<>("This is already the main photo", HttpStatus.BAD_REQUEST);
		}

		Optional<Photo> currentMainPhoto = photoRepository.findMainPhotoForUser(userId);
		if (currentMainPhoto.isPresent()) {
			currentMainPhoto.get().setMain(false);
			photoRepository.save(currentMainPhoto.get());
		}

		photo.get().setMain(true);
		photoRepository.save(photo.get());

		PhotoForReturnDto photoForReturnDto = dozerBeanMapper.map(photo.get(), PhotoForReturnDto.class);

		return new ApiResponse<>(photoForReturnDto, HttpStatus.OK);
	}

	@Override
	public ApiResponse<?> deletePhoto(int userId, int photoId, HttpServletRequest request) {
		
		Optional<User> user = userRepository.findOneWithPhotos(userId);
		if (!user.isPresent()) {
			return new ApiResponse<>("User not found", HttpStatus.NOT_FOUND);
		}
		
		int currentUserId = jwtTokenProvider.getUserIdFromJWT(jwtAuthenticationFilter.getJwtFromRequest(request));
		if (currentUserId != userId) {
			return new ApiResponse<>("User not authorized", HttpStatus.UNAUTHORIZED);
		}

		Optional<Photo> photo = photoRepository.findById(photoId);
		if (!photo.isPresent()) {
			return new ApiResponse<>("Photo not found", HttpStatus.NOT_FOUND);
		}
		else if (photo.get().isMain()) {
			return new ApiResponse<>("You can not delete the main photo", HttpStatus.BAD_REQUEST);
		}

		if (photo.get().getPublicId() != null) {
			String url = "";
			String publicId = "";
			Map result = new HashMap();
			
			result = cloudinaryService.deletePhoto(photo.get().getPublicId());

			/*Cloudinary cloudinary = new Cloudinary(
					ObjectUtils.asMap("cloud_name", mCloudName, "api_key", mApiKey, "api_secret", mApiSecret));

			try {
				result = cloudinary.uploader().destroy(photo.get().getPublicId(), ObjectUtils.emptyMap());
			} catch (Exception e) {
				return new ResponseEntity<String>("Could not delete the photo", HttpStatus.BAD_REQUEST);
			}*/
			
			if(result.containsValue("ok")){
				user.get().getPhotos().removeIf(p-> p.getId() == photo.get().getId());
				userRepository.save(user.get());
			}
		}
		
		if (photo.get().getPublicId() == null) {
			//Predicate<Photo> personPredicate = p-> p.getId() == photo.get().getId();
			user.get().getPhotos().removeIf(p-> p.getId() == photo.get().getId());
			userRepository.save(user.get());
		}

		return new ApiResponse<>("Photo succesfully deleted", HttpStatus.NO_CONTENT);
	}

}
