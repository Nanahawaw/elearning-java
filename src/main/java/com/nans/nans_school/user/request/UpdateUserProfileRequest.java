package com.nans.nans_school.user.request;

import lombok.Data;

@Data
public class UpdateUserProfileRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String bio;
}
