package com.nans.nans_school.course.request;

import lombok.Data;

@Data
public class CreateCourseRequest {

    private String title;
    private String description;
    private int price;
    private Long tutorId;
}
