package com.nans.nans_school.user.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String bio;
    private String avatarUrl;
    private String phoneNumber;
    private String role;
}
