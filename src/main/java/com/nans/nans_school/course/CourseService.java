package com.nans.nans_school.course;

import com.nans.nans_school.course.response.GetCourseResponse;
import com.nans.nans_school.utils.CourseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public List<GetCourseResponse> findAllCourses(){
        List<Course>getCourses = courseRepository.findAll();

        return getCourses.stream().map(CourseMapper:: courseResponse)
                .collect(Collectors.toList());

    }

    public List<GetCourseResponse> findCoursesByTutorId(Long tutorId){
        log.info("Fetching courses for tutor with ID: {}", tutorId);
        List<Course> courses = courseRepository.findByTutorId(tutorId);
        log.info("Found {} courses", courses.size());
        return courses.stream().map(CourseMapper::courseResponse).collect(Collectors.toList());
    }
}
