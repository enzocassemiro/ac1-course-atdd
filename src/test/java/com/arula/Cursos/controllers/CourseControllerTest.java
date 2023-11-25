package com.arula.Cursos.controllers;

import com.arula.Cursos.entity.Course;
import com.arula.Cursos.entity.Student;
import com.arula.Cursos.services.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @Test
    public void testCreateCourse() throws Exception {
        List<Student> studentList = new ArrayList<>();
        Student student = new Student(1L, "João Cleber", "489.426.430-78", "25/02/2000");
        studentList.add(student);
        Course course = new Course(2L, "Curso de python", 50.0, 60, studentList);

        when(courseService.createCourse(any(Course.class))).thenReturn(course);

        mockMvc.perform(post("/course/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Course\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetCourse() throws Exception {
        List<Student> studentList = new ArrayList<>();
        Student student = new Student(1L, "João Cleber", "489.426.430-78", "25/02/2000");
        studentList.add(student);
        Course course = new Course(2L, "Curso de python", 50.0, 60, studentList);

        when(courseService.getCourseById(2L)).thenReturn(Optional.of(course));

        mockMvc.perform(get("/course/v1/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
