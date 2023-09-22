package com.arula.Cursos.entity;

import com.arula.Cursos.enums.StudentLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
public class Student {
    private String name;
    private String nationalId;
    private String birthDate;
    private Integer level;
    private ArrayList<Course> courses;

    public Student(String name, String nationalId, String birthDate) {
        this.name = name;
        this.nationalId = nationalId;
        this.birthDate = birthDate;
        this.level = StudentLevel.DEFAULT.getLimit();
        this.courses = new ArrayList<Course>();
    }

    public Student(String name, String nationalId, String birthDate, Integer level) {
        this.name = name;
        this.nationalId = nationalId;
        this.birthDate = birthDate;
        this.level = level;
        this.courses = new ArrayList<Course>();
    }

    public void addCourse(Course course) {
        if (this.courses.size() > this.level) {
            throw new RuntimeException("Cannot add more courses, exceed the limit");
        } else this.courses.add(course);
    }
}
