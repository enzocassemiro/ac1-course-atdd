package com.arula.Cursos.repositories;

import com.arula.Cursos.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}