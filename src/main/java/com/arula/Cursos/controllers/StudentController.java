package com.arula.Cursos.controllers;

import com.arula.Cursos.dto.StudentDTO;
import com.arula.Cursos.entity.Student;
import com.arula.Cursos.mapper.StudentMapper;
import com.arula.Cursos.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("v1")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody Student student) {
        StudentDTO studentDTO = StudentMapper.mapStudentToStudentDTO(studentService.createStudent(student));
        return new ResponseEntity<>(studentDTO, HttpStatus.CREATED);
    }

    @GetMapping("v1")
    public ResponseEntity<StudentDTO> getStudentByNationalId(@RequestHeader(name = "nationalId") String nationalId) {
        if (studentService.getStudentByNationalId(nationalId).isPresent()) {
            StudentDTO studentDTO = StudentMapper.mapStudentToStudentDTO(studentService.getStudentByNationalId(nationalId).get());
            return new ResponseEntity<>(studentDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("v1")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        List<StudentDTO> studentDTOs = students.stream()
                .map(StudentMapper::mapStudentToStudentDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentDTOs);
    }
}
