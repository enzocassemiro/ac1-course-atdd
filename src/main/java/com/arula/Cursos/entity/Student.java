package com.arula.Cursos.entity;

import com.arula.Cursos.enums.StudentLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nationalId;

    @Column(nullable = false)
    private String birthDate;

    @Column(nullable = false)
    private Integer level;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses;

    public Student(String name, String nationalId, String birthDate) {
        this.name = name;
        this.nationalId = nationalId;
        this.birthDate = birthDate;
        this.level = StudentLevel.DEFAULT.getLimit();
        this.courses = new ArrayList<>();
    }

    public Student(Long id, String name, String nationalId, String birthDate) {
        this.id = id;
        this.name = name;
        this.nationalId = nationalId;
        this.birthDate = birthDate;
        this.level = StudentLevel.DEFAULT.getLimit();
        this.courses = new ArrayList<>();
    }


    public Student(String name, String nationalId, String birthDate, Integer level) {
        this.name = name;
        this.nationalId = nationalId;
        this.birthDate = birthDate;
        this.level = level;
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        if (this.courses.size() > this.level) {
            throw new RuntimeException("Cannot add more courses, exceed the limit");
        } else this.courses.add(course);
    }
}
