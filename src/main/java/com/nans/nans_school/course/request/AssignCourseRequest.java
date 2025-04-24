package com.nans.nans_school.course.request;

import lombok.Data;

@Data
public class AssignCourseRequest {

    private  int courseId;
    private  Long tutorId;
}
