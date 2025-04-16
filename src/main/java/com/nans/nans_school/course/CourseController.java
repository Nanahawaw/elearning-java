package com.nans.nans_school.course;

import com.nans.nans_school.course.response.GetCourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
@GetMapping
public ResponseEntity<List<GetCourseResponse>> findAllCourses(){
  return ResponseEntity.ok(courseService.findAllCourses());
}

@GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<GetCourseResponse>> findAllCoursesByTutorId(
@PathVariable Long tutorId){
    return ResponseEntity.ok(courseService.findCoursesByTutorId(tutorId));
}
}
