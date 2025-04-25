package com.nans.nans_school.course;

import com.nans.nans_school.course.request.UpdateCourseRequest;
import com.nans.nans_school.course.response.GetCourseResponse;
import com.nans.nans_school.utils.exceptions.NotFoundException;
import com.nans.nans_school.utils.mapper.CourseMapper;
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

        List<Course> courses = courseRepository.findByTutorId(tutorId);
        return courses.stream().map(CourseMapper::courseResponse).collect(Collectors.toList());
    }

    public String publishUnpublishCourse(int CourseId, boolean publish){

        Course course = courseRepository.findById(CourseId).orElseThrow(() -> new NotFoundException("Course not found"));
        course.setPublished(publish);
courseRepository.save(course);
return publish? "Published" : "Unpublished";

    }

  public  GetCourseResponse updateCourse(int CourseId, UpdateCourseRequest request){

        Course course = courseRepository.findById(CourseId).orElseThrow(() -> new NotFoundException("Course not found"));


    course.setTitle(request.getTitle());
    course.setDescription(request.getDescription());
    course.setPrice(request.getPrice());
    course.setTutor(course.getTutor());

    Course updatedCourse = courseRepository.save(course);

    return CourseMapper.courseResponse(updatedCourse);
    }

    public String DeleteCourse(int CourseId){

        courseRepository.deleteById(CourseId);
        return "Deleted";
    }
}
