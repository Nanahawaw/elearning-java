package com.nans.nans_school.course.response;

import lombok.Data;

@Data
public class GetCourseResponse {

    private int id;
    private String title;
    private String description;
    private boolean published;
    private String duration;
    private int price;
    private String thumbnailUrl;
    private String tutorName;
}
