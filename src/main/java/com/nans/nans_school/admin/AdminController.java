package com.nans.nans_school.admin;

import com.nans.nans_school.admin.request.InviteTutorRequest;
import com.nans.nans_school.admin.response.TutorInviteResponse;
import com.nans.nans_school.course.request.CreateCourseRequest;
import com.nans.nans_school.course.response.CourseCreationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

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
    ) throws BadRequestException {
        return ResponseEntity.ok(adminService.createCourse(request));
    }

}
