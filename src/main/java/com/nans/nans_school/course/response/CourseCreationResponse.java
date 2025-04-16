package com.nans.nans_school.course.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseCreationResponse {

    private int id;
    private String title;
    private String description;
    private String tutorName;
    private int price;
}
