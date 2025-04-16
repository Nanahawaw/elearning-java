package com.nans.nans_school.admin.response;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class TutorInviteResponse {
    private String firstName;
    private String lastName;
    private String email;
}
