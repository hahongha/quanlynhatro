package com.utc.rental.rental.service.impl;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CloudService {
	private final Cloudinary cloudinary;

    public CloudService(@Value("${cloudinary.cloud-name}") String cloudName,
                             @Value("${cloudinary.api-key}") String apiKey,
                             @Value("${cloudinary.api-secret}") String apiSecret) {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        cloudinary = new Cloudinary(config);
    }

    public String uploadBookCover(MultipartFile file, String isbn) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("folder", "book-covers");
            params.put("public_id", isbn);

            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), params);
            return (String) uploadResult.get("secure_url");
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }
    }

    public String uploadEmployeeAvatar(MultipartFile file, String userID) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("folder", "employee-avatars");
            params.put("public_id", userID);

            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), params);
            return (String) uploadResult.get("secure_url");
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }
    }
    
    public List<String> uploadMultipartFile( MultipartFile[] files){
    	 List<String> uploadedFileUrls = new ArrayList<>();
    	 if (files == null || files.length == 0) {
             return uploadedFileUrls;
         }
         // Duyệt qua từng file và upload lên Cloudinary
         for (MultipartFile file : files) {
             try {
                 // Upload file lên Cloudinary
                 Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

                 // Lấy URL của ảnh vừa upload
                 String imageUrl = (String) uploadResult.get("url");
                 uploadedFileUrls.add(imageUrl);
                 
             } catch (IOException e) {
            	 throw new RuntimeException("Failed to upload image", e);
             } catch (Exception e) {
            	 throw new RuntimeException("Failed to upload image", e);
             }
         }
         return uploadedFileUrls;

    }
    
}
