package com.nans.nans_school.courseModule;

import com.nans.nans_school.course.Course;
import com.nans.nans_school.course.CourseRepository;
import com.nans.nans_school.courseModule.request.CreateModuleRequest;
import com.nans.nans_school.courseModule.response.CreateModuleResponse;
import com.nans.nans_school.utils.exceptions.DuplicateException;
import com.nans.nans_school.utils.exceptions.NotFoundException;
import com.nans.nans_school.utils.mapper.ModuleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseModuleService {

    private final CourseRepository courseRepository;
    private final CourseModuleRepository moduleRepository;

    public CreateModuleResponse createCourseModule(int courseId, CreateModuleRequest request) {

        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new NotFoundException("Course not found")
        );

if(moduleRepository.existsByTitle(request.getTitle())) {
    throw new DuplicateException("Course module with title " + request.getTitle() + " already exists");
}
        CourseModule module = new CourseModule();
        module.setCourse(course);
        module.setTitle(request.getTitle());
        module.setDescription(request.getDescription());
        CourseModule savedResponse = moduleRepository.save(module);


        return ModuleMapper.mapper(savedResponse);
    }


}

