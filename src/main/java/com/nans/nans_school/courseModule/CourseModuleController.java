package com.nans.nans_school.courseModule;

import com.nans.nans_school.courseModule.request.CreateModuleRequest;
import com.nans.nans_school.courseModule.response.CreateModuleResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course-module")
@RequiredArgsConstructor
public class CourseModuleController {

    private final CourseModuleRepository courseModuleRepository;
    private final CourseModuleService courseModuleService;

    @PostMapping("/{courseId}/create")
    ResponseEntity<CreateModuleResponse> createModule(@PathVariable int courseId, @RequestBody CreateModuleRequest request) throws BadRequestException {

        return ResponseEntity.ok(courseModuleService.createCourseModule(courseId, request));

    }
}
