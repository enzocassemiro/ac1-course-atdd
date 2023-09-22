package com.arula.Cursos.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course {
    private String name;
    private Integer hours;
    private Integer availableSpots;

    public boolean validateStudentOnCourse(Student student) {
        for (Course course : student.getCourses()) {
            if (course.getName().equals(this.name)) {
                return true;
            }
        }
        return false;
    }
}
