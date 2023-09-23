package com.arula.Cursos.dto;

import com.arula.Cursos.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Long id;
    private String name;
    private Double hours;
    private Integer availableSpots;
    private List<Student> students;
}
