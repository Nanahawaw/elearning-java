package com.nans.nans_school.service;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public String uploadAvatar(MultipartFile file){
        try{
            Map <String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), Map.of(
                    "folder", "avatars",
                    "resource_type", "image"
            ));
            return (String) uploadResult.get("secure_url");

        }
        catch(IOException e){
            log.error("Failed to upload image to Cloudinary", e);
            throw new RuntimeException("Failed to upload image", e);
        }
    }
}
