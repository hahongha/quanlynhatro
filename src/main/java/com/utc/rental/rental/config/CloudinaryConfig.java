package com.utc.rental.rental.config;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        // Thay thế bằng các thông số thực tế của bạn
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dlyprrqnn",
                "api_key", "891924155736948",
                "api_secret", "0zXOVLc4rzP4VPSUo1-aE1FumxE"
        ));
    }
}
