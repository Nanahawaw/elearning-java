package com.nans.nans_school.admin;

import com.nans.nans_school.admin.request.InviteTutorRequest;
import com.nans.nans_school.admin.response.TutorInviteResponse;
import com.nans.nans_school.course.Course;
import com.nans.nans_school.course.CourseRepository;
import com.nans.nans_school.course.request.AssignCourseRequest;
import com.nans.nans_school.course.request.CreateCourseRequest;
import com.nans.nans_school.course.response.CourseCreationResponse;
import com.nans.nans_school.user.User;
import com.nans.nans_school.user.UserRepository;
import com.nans.nans_school.utils.enums.Role;
import com.nans.nans_school.utils.exceptions.DuplicateException;
import com.nans.nans_school.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;

    public TutorInviteResponse tutorInvitation ( InviteTutorRequest request) throws BadRequestException{

        //validate the request

        if(request.getEmail() == null || request.getLastName() == null || request.getFirstName() == null){
            throw new BadRequestException("Email, first name and last name is required");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateException("User already exists");

        }
        String randomPassword = generatePassword();
        try {

            User tutor = new User();
            tutor.setEmail(request.getEmail());
            tutor.setPassword(passwordEncoder.encode(randomPassword));
            tutor.setRole(Role.TUTOR);
            tutor.setFirstName(request.getFirstName());
            tutor.setLastName(request.getLastName());

            userRepository.save(tutor);
            //send email

            return TutorInviteResponse.builder()
                    .firstName(tutor.getFirstName())
                    .lastName(tutor.getLastName())
                    .email(tutor.getEmail())
                    .build();
        }catch (Exception e){
            throw new InternalException("Something went wrong");
        }
    }


    public CourseCreationResponse createCourse (CreateCourseRequest request) throws AccessDeniedException {

        User tutor = userRepository.findById(request.getTutorId())
                .orElseThrow(() -> new NotFoundException("User does not exist"));

        if(tutor.getRole() != Role.TUTOR){
            throw new AccessDeniedException("Only users with TUTOR role can add courses");
        }

if(courseRepository.existsByTitle(request.getTitle())){
    throw new DuplicateException("Course already exists");
}

        Course course = new Course();

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        course.setTutor(tutor);

courseRepository.save(course);
        return CourseCreationResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .price(course.getPrice())
                .tutorName(tutor.getFirstName() + " " + tutor.getLastName())
                .build();

    }



    private String generatePassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

}
