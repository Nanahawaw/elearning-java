package com.nans.nans_school.utils.mapper;

import com.nans.nans_school.courseModule.CourseModule;
import com.nans.nans_school.courseModule.response.CreateModuleResponse;

public class ModuleMapper {
    public static CreateModuleResponse mapper (CourseModule module) {
        CreateModuleResponse response = new CreateModuleResponse();

        response.setId(module.getId());
        response.setTitle(module.getTitle());
        response.setDescription(module.getDescription());
        response.setCourseId(module.getCourse().getId());
        return response;
    }
}
