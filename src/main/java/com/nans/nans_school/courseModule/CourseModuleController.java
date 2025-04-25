package com.nans.nans_school.courseModule;

import com.nans.nans_school.courseModule.request.CreateModuleRequest;
import com.nans.nans_school.courseModule.response.CreateModuleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course-module")
@RequiredArgsConstructor
public class CourseModuleController {

    private final CourseModuleService courseModuleService;

    @PreAuthorize("hasRole('TUTOR')")
    @PostMapping("/{courseId}/create")
    ResponseEntity<CreateModuleResponse> createModule(@PathVariable int courseId, @RequestBody CreateModuleRequest request) {
        try {
            return ResponseEntity.ok(courseModuleService.createCourseModule(courseId, request));
        } catch (AccessDeniedException ex) {
            throw new AccessDeniedException(ex.getMessage());
        }

    }
}
