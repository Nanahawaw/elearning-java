package com.nans.nans_school.admin;

import com.nans.nans_school.admin.request.InviteTutorRequest;
import com.nans.nans_school.admin.response.TutorInviteResponse;
import com.nans.nans_school.course.CourseService;
import com.nans.nans_school.course.request.CreateCourseRequest;
import com.nans.nans_school.course.request.UpdateCourseRequest;
import com.nans.nans_school.course.response.CourseCreationResponse;
import com.nans.nans_school.course.response.GetCourseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final CourseService courseService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/invite-tutor")
    public ResponseEntity<TutorInviteResponse> inviteTutor(
            @Valid
            @RequestBody
            InviteTutorRequest request
    ) throws BadRequestException {
        return ResponseEntity.ok(adminService.tutorInvitation(request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-course")
    public ResponseEntity<CourseCreationResponse> createCourse(
            @Valid
            @RequestBody
            CreateCourseRequest request
    ) throws  AccessDeniedException {
        return ResponseEntity.ok(adminService.createCourse(request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("course/{courseId}/publish")
    public ResponseEntity<?> publishCourse(
            @PathVariable int courseId,
            @RequestParam boolean publish
    ) {
        return ResponseEntity.ok(courseService.publishUnpublishCourse(courseId, publish));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("course/{courseId}/edit")
    public ResponseEntity<GetCourseResponse> updateCourse(
            @PathVariable int courseId,
            @RequestBody UpdateCourseRequest request
    ) {
        return ResponseEntity.ok(courseService.updateCourse(courseId, request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("course/{courseId}/delete")
    public ResponseEntity<String> deleteCourse(
            @PathVariable int courseId
    ) {
        return ResponseEntity.ok(courseService.DeleteCourse(courseId));
    }
}
