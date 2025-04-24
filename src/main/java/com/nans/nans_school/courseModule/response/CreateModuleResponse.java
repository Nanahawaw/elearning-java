package com.nans.nans_school.courseModule.response;

import lombok.Data;

@Data
public class CreateModuleResponse {

    private Long id;
    private String title;
    private String description;
    private int courseId;
}
