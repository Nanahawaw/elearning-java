package com.nans.nans_school.utils.mapper;

import com.nans.nans_school.course.Course;
import com.nans.nans_school.course.response.GetCourseResponse;

public class CourseMapper {

    public static GetCourseResponse courseResponse(Course course) {

        GetCourseResponse response = new GetCourseResponse();
        response.setId(course.getId());
        response.setTitle(course.getTitle());
        response.setDescription(course.getDescription());
        response.setPublished(course.isPublished());
        response.setThumbnailUrl(course.getThumbnailUrl());
        response.setDuration(course.getDuration());
        response.setPrice(course.getPrice());
        // Concatenate tutor's first and last name
        String tutorFullName = course.getTutor().getFirstName() + " " + course.getTutor().getLastName();
        response.setTutorName(tutorFullName);

        return response;
    }
}
