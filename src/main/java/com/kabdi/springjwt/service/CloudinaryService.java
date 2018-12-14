package com.kabdi.springjwt.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	@Qualifier("com.cloudinary.cloud_name")
	String mCloudName;

	@Autowired
	@Qualifier("com.cloudinary.api_key")
	String mApiKey;

	@Autowired
	@Qualifier("com.cloudinary.api_secret")
	String mApiSecret;

	public Map<String, Object> uploadPhoto(MultipartFile file) {

		Map<String, Object> result = new HashMap<String, Object>();

		Cloudinary cloudinary = new Cloudinary(
				ObjectUtils.asMap("cloud_name", mCloudName, "api_key", mApiKey, "api_secret", mApiSecret));

		try {
			result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("transformation",
					new Transformation().width(500).height(500).crop("thumb").gravity("face")));
		} catch (Exception e) {
			// return new ResponseEntity<String>("Could not add the photo",
			// HttpStatus.BAD_REQUEST);
		}

		return result;
	}

	public Map<String, Object> deletePhoto(String publicId) {

		Map<String, Object> result = new HashMap<String, Object>();

		Cloudinary cloudinary = new Cloudinary(
				ObjectUtils.asMap("cloud_name", mCloudName, "api_key", mApiKey, "api_secret", mApiSecret));

		try {
			result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
		} catch (Exception e) {
			// return new ResponseEntity<String>("Could not add the photo",
			// HttpStatus.BAD_REQUEST);
		}

		return result;
	}
}
