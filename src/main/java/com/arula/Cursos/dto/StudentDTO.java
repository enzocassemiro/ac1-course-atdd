package com.arula.Cursos.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    private String nationalId;
    private String birthDate;
    private Integer level;
    private List<CourseDTO> courses;
}
