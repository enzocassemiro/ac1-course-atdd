package com.arula.Cursos.controllers;

import com.arula.Cursos.dto.StudentDTO;
import com.arula.Cursos.entity.Student;
import com.arula.Cursos.services.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    private Student student;
    private StudentDTO studentDTO;

    @BeforeEach
    public void setup() {
        student = new Student("Test Name", "123456", "2000-01-01");
        studentDTO = new StudentDTO();
        studentDTO.setId(1L);
        studentDTO.setName("Test Name");
        studentDTO.setNationalId("123456");
        studentDTO.setBirthDate("2000-01-01");
        studentDTO.setLevel(1);
        studentDTO.setCourses(new ArrayList<>());
    }


    @Test
    public void testCreateStudent() throws Exception {
        when(studentService.createStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/student/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetStudent() throws Exception {
        when(studentService.getStudentByNationalId(any(String.class))).thenReturn(Optional.of(student));

        mockMvc.perform(get("/student/v1")
                        .header("nationalId", "123456"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetStudentNotFound() throws Exception {
        when(studentService.getStudentByNationalId(any(String.class))).thenReturn(Optional.empty());

        mockMvc.perform(get("/student/v1")
                        .header("nationalId", "123456"))
                .andExpect(status().isNotFound());
    }
}
