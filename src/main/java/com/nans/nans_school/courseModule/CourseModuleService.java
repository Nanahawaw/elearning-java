package com.nans.nans_school.courseModule;

import com.nans.nans_school.course.Course;
import com.nans.nans_school.course.CourseRepository;
import com.nans.nans_school.courseModule.request.CreateModuleRequest;
import com.nans.nans_school.courseModule.response.CreateModuleResponse;
import com.nans.nans_school.utils.mapper.ModuleMapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseModuleService {

    private final CourseRepository courseRepository;
    private final CourseModuleRepository moduleRepository;

    public CreateModuleResponse createCourseModule(int courseId, CreateModuleRequest request) throws BadRequestException {

        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new BadRequestException("Course not found")
        );
        CourseModule module = new CourseModule();
        module.setCourse(course);
        module.setTitle(request.getTitle());
        module.setDescription(request.getDescription());
        CourseModule savedResponse = moduleRepository.save(module);


        return ModuleMapper.mapper(savedResponse);
    }


}

