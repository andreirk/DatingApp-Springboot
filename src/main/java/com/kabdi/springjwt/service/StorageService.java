package com.kabdi.springjwt.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	private final Path rootLocation = Paths.get("upload-dir");
    private static String ABS_PATH = "C:/Project/";
	
	public void storePath(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
	}
	
	public void store(MultipartFile file, String code) {
		if(!new File(ABS_PATH).exists()) {
			new File(ABS_PATH).mkdirs();
		}
		try {
			//transfer the file to the location
			file.transferTo(new File(ABS_PATH + code + ".jpg"));
			System.out.println("File transferred to " + ABS_PATH + code + ".jpg");
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void store(MultipartFile file) {
		if(!new File(ABS_PATH).exists()) {
			new File(ABS_PATH).mkdirs();
		}
		try {
			//transfer the file to the location
			file.transferTo(new File(ABS_PATH + file.getOriginalFilename()));
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	public Resource loadFile(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}
	
}
