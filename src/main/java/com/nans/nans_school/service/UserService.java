package com.nans.nans_school.service;

import com.nans.nans_school.dto.UpdateUserProfileRequest;
import com.nans.nans_school.entities.User;
import com.nans.nans_school.repositories.UserRepository;
import com.nans.nans_school.utils.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public User getLoggedInUser(String token) {
        String email = jwtService.extractUsername(token);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserProfileResponse getProfile(String token){
        User user = getLoggedInUser(token);
        return mapToProfileResponse(user);
    }

    public UserProfileResponse updateProfile(String token, UpdateUserProfileRequest request)
    {
        User user = getLoggedInUser(token);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setBio(request.getBio());

        User updated = userRepository.save(user);
        return mapToProfileResponse(updated);
    }

    private UserProfileResponse mapToProfileResponse(User user){
        return UserProfileResponse.builder()
                . id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .bio(user.getBio())
                .phoneNumber(user.getPhoneNumber())
                .avatarUrl(user.getAvatarUrl())
                .role(user.getRole().name())
                .build();
    }

    public UserProfileResponse updateAvatar(String token, String imageUrl) {
        User user = getLoggedInUser(token);
        user.setAvatarUrl(imageUrl);
        userRepository.save(user);
        return mapToProfileResponse(user);
    }
}
