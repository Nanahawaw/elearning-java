package com.nans.nans_school.courseModule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseModuleRepository extends JpaRepository<CourseModule, Integer> {
    List<CourseModule> findByCourseId(int courseId);


}
