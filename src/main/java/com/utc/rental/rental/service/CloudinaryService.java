package com.utc.rental.rental.service;

import java.util.Base64;
import java.util.Map;

import javax.imageio.ImageReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.utc.rental.rental.dto.image.ImageDTO;
import com.utc.rental.rental.entity.Image;
import com.utc.rental.rental.repository.ImageRepo;

import io.jsonwebtoken.io.IOException;

public interface CloudinaryService {
	//tải ảnh lên folder và trả về url
	ImageDTO uploadAnhToFolder(String folder, byte[] imageBytes);
	String deleteFile(String publicId, Long id);
	String uploadImage(MultipartFile file, String publicId);
}
@Service
class CloudinaryServiceImpl implements CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;
    
    @Autowired
    private ImageRepo imageRepo;

	@Override
	public ImageDTO uploadAnhToFolder(String folder, byte[] imageBytes) {
		ImageDTO imageDTO = new ImageDTO();
		 try {

	            // Upload lên Cloudinary với folder
	            Map<String, Object> options = ObjectUtils.asMap("folder", folder);
	            Map uploadResult = cloudinary.uploader().upload(imageBytes, options);

	            String imageUrl = uploadResult.get("secure_url").toString();
	            String cloudinaryId = uploadResult.get("public_id").toString();
	            imageDTO.setCloudinaryId(cloudinaryId);
	            imageDTO.setImageUrl(imageUrl); 
	            imageRepo.save(new Image(0L, imageUrl, cloudinaryId, true));
	        } catch (Exception e) {
	            throw new RuntimeException("Upload failed: " + e.getMessage(), e);
	        }
		 
		 return imageDTO;
	}
	// Xóa ảnh bằng public_id
	@Override
    public String deleteFile(String publicId, Long id) {
        try {
            Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            if ("ok".equals(result.get("result"))) {
                imageRepo.deleteById(id);
                return "Xóa thành công: " + publicId;
            } else {
                return "Không tìm thấy ảnh!";
            }
        } catch (Exception e) {
            return "Lỗi khi xóa ảnh: " + e.getMessage();
        }
    }
	@Override
	public String uploadImage(MultipartFile file, String publicId) {
		try {
			
			// TODO: handle exception
			Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
					"public_id", publicId,  // Dùng public_id cũ để ghi đè ảnh cũ
					"overwrite", true,       // Cho phép ghi đè ảnh cũ
					"invalidate", true 
					));
			 return (String) uploadResult.get("secure_url"); // Trả về URL ảnh mới (vẫn giữ nguyên đường link)
		} catch (Exception e) {
			e.printStackTrace();
			 return "Lỗi khi upload ảnh: " + e.getMessage();
		}
    }

}
