package com.arula.Cursos.mapper;

import com.arula.Cursos.dto.CourseDTO;
import com.arula.Cursos.dto.StudentDTO;
import com.arula.Cursos.entity.Course;
import com.arula.Cursos.entity.Student;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {

    public static StudentDTO mapStudentToStudentDTO(Student student) {
        List<CourseDTO> courseDTOs = Collections.emptyList();

        if (student.getCourses() != null) {
            courseDTOs = student.getCourses().stream()
                    .map(CourseMapper::mapCourseToCourseDTO)
                    .collect(Collectors.toList());
        }

        return new StudentDTO(
            student.getId(),
            student.getName(),
            student.getNationalId(),
            student.getBirthDate(),
            student.getLevel(),
            courseDTOs);
    }
}
