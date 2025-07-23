package com.Config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dp0hmdtay",
            "api_key", "634375167394564",
            "api_secret", "AqT8YNthCDIW4N9Qt4G4_7AuDgI",
            "secure", true
        ));
    }
}
