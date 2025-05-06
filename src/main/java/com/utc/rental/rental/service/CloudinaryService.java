package com.utc.rental.rental.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import io.jsonwebtoken.io.IOException;

public interface CloudinaryService {
	String uploadImage(MultipartFile file, String folder);

	List<String> uploadMultipartFile(MultipartFile[] files, String folder);
}

@Service
class CloudinaryServiceImpl implements CloudinaryService {

	@Autowired
	private Cloudinary cloudinary;

	@Override
	public String uploadImage(MultipartFile file, String folder) {
		if (file == null)
			return null;
		try {
			// Upload file lên Cloudinary
			Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

			// Lấy URL của ảnh vừa upload
			String imageUrl = (String) uploadResult.get("url");
			imageUrl = imageUrl.replace("http://", "https://");

			return imageUrl;

		} catch (IOException e) {
			throw new RuntimeException("Failed to upload image", e);
		} catch (Exception e) {
			throw new RuntimeException("Failed to upload image", e);
		}
	}

	@Override
	public List<String> uploadMultipartFile(MultipartFile[] files, String folder) {
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
				imageUrl = imageUrl.replace("http://", "https://");
				uploadedFileUrls.add(imageUrl);

				System.err.println(imageUrl);

			} catch (IOException e) {
				throw new RuntimeException("Failed to upload image", e);
			} catch (Exception e) {
				throw new RuntimeException("Failed to upload image", e);
			}
		}
		return uploadedFileUrls;
	}

}
