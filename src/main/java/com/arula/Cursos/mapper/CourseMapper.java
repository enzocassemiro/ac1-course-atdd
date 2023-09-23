package com.arula.Cursos.mapper;

import com.arula.Cursos.dto.CourseDTO;
import com.arula.Cursos.entity.Course;

import java.util.List;

public class CourseMapper {

    public static CourseDTO mapCourseToCourseDTO(Course course) {
        return new CourseDTO(
            course.getId(),
            course.getName(),
            course.getHours(),
            course.getAvailableSpots(),
            course.getStudents());
    }
}
