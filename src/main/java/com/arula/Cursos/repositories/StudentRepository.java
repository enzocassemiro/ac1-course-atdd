package com.arula.Cursos.repositories;

import com.arula.Cursos.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
