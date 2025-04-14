package com.nans.nans_school.dto;

import lombok.Data;

@Data
public class UpdateUserProfileRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String bio;
}
