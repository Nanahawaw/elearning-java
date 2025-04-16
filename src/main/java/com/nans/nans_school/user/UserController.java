package com.nans.nans_school.user;
import com.nans.nans_school.user.request.UpdateUserProfileRequest;
import com.nans.nans_school.fileHandler.CloudinaryService;
import com.nans.nans_school.user.response.UserProfileResponse;
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
    ){
        return ResponseEntity.ok(userService.getProfile());
    }

    @PutMapping(value = "/me/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserProfileResponse> updateAvatar(
            @RequestPart("file")MultipartFile file
            ){
        String imageUrl = cloudinaryService.uploadAvatar(file);
        return ResponseEntity.ok(userService.updateAvatar(imageUrl));

    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfileResponse> editProfile(
            @RequestBody UpdateUserProfileRequest request
    ){
        return ResponseEntity.ok(userService.updateProfile(request));
    }


}
