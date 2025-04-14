package com.nans.nans_school.controllers;

import com.nans.nans_school.dto.UpdateUserProfileRequest;
import com.nans.nans_school.service.CloudinaryService;
import com.nans.nans_school.service.UserService;
import com.nans.nans_school.utils.UserProfileResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CloudinaryService cloudinaryService;

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getMyProfile(
            @RequestHeader("Authorization") String authHeader
    ){
        String token = extractToken(authHeader);
        return ResponseEntity.ok(userService.getProfile(token));
    }

    @PutMapping(value = "/me/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserProfileResponse> updateAvatar(
            @RequestHeader("Authorization") String authHeader,
            @RequestPart("file")MultipartFile file
            ){
        String token = extractToken(authHeader);
        String imageUrl = cloudinaryService.uploadAvatar(file);
        return ResponseEntity.ok(userService.updateAvatar(token,imageUrl));

    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfileResponse> editProfile(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody UpdateUserProfileRequest request
    ){
        String token = extractToken(authHeader);
        return ResponseEntity.ok(userService.updateProfile(token, request));
    }

    private String extractToken(String authHeader) {
        return authHeader.startsWith("Bearer ") ? authHeader.substring(7): authHeader;
    }


}
