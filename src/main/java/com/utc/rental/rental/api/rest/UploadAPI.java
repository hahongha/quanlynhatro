package com.utc.rental.rental.api.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.utc.rental.rental.dto.ImageBase64;
import com.utc.rental.rental.dto.image.ImageDTO;
import com.utc.rental.rental.service.CloudinaryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/upload")
public class UploadAPI {
	@Autowired
    private CloudinaryService cloudinaryService;

    // Upload bằng file
    @PostMapping("/file")
    public ResponseEntity<ImageDTO> uploadImageFile(
            @RequestParam("file") MultipartFile file) {
    	ImageDTO imageDTO = new ImageDTO();
		try {
			imageDTO = cloudinaryService.uploadAnhToFolder("TEST", file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResponseEntity.ok(imageDTO);
    }

    // Upload bằng Base64
    @PostMapping("/base64")
    public ResponseEntity<ImageDTO> uploadImageBase64(@RequestBody @Valid ImageBase64 imageBase64) {
    	ImageDTO imageDTO = new ImageDTO();
		try {
			byte[] anh = Base64.getDecoder().decode(imageBase64.getBase64());
			imageDTO = cloudinaryService.uploadAnhToFolder("TEST", anh);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResponseEntity.ok(imageDTO);
    }
    @PostMapping("/files")
    public ResponseEntity<List<ImageDTO>> uploadImages(@RequestParam("files") MultipartFile[] files) {
    	List<ImageDTO>imageUrls = new ArrayList<ImageDTO>();
        for (MultipartFile file : files) {
            ImageDTO imageDTO = new ImageDTO();
            try {
    			imageDTO = cloudinaryService.uploadAnhToFolder("TEST", file.getBytes());
    			imageUrls.add(imageDTO);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
        return ResponseEntity.ok(imageUrls);
    }
    
    @PostMapping("/user/{userId}")
    public ResponseEntity<String> uploadImage(@PathVariable String userId, @RequestParam("file") MultipartFile file) {
        try {
            String publicId = "users/user_" + userId; // Định danh ảnh theo userId để ghi đè
            String imageUrl = cloudinaryService.uploadImage(file, publicId);

            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
        	e.printStackTrace();
            return ResponseEntity.internalServerError().body("Lỗi khi upload ảnh: " + e.getMessage());
        }
    }
    

}
