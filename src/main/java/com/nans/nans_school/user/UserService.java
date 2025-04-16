package com.nans.nans_school.user;

import com.nans.nans_school.user.request.UpdateUserProfileRequest;
import com.nans.nans_school.auth.service.JwtService;
import com.nans.nans_school.user.response.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

   public User getCurrentUser() {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String email = ((UserDetails) authentication.getPrincipal()).getUsername();
       return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
   }

    public UserProfileResponse getProfile(){
        User user = getCurrentUser();
        return mapToProfileResponse(user);
    }

    public UserProfileResponse updateProfile( UpdateUserProfileRequest request)
    {
        User user = getCurrentUser();
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

    public UserProfileResponse updateAvatar( String imageUrl) {
        User user = getCurrentUser();
        user.setAvatarUrl(imageUrl);
        userRepository.save(user);
        return mapToProfileResponse(user);
    }
}
