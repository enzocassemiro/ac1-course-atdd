package com.arula.Cursos.services;

import com.arula.Cursos.entity.Course;
import com.arula.Cursos.entity.Student;
import com.arula.Cursos.exceptions.NotFoundException;
import com.arula.Cursos.repositories.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        courseService = new CourseService(courseRepository);
    }

    @Test
    void shouldReturnAllCourses() {
        List<Student> studentList = new ArrayList<>();
        Student student = new Student(1L, "João Cleber", "489.426.430-78", "25/02/2000");
        studentList.add(student);
        Course course = new Course(2L, "Curso de python", 50.0, 60, studentList);
        Course course2 = new Course(3L, "Curso de java", 80.0, 20, studentList);

        when(courseRepository.findAll()).thenReturn(new ArrayList<>(List.of(course, course2)));

        List<Course> courses = courseService.getAllCourses();

        assertEquals(2, courses.size());
        assertTrue(courses.contains(course));
        assertTrue(courses.contains(course2));
    }


    @Test
    public void shouldThrowNotFoundExceptionIfCourseDoesNotExist() {
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> courseService.getCourseById(1L));
    }

    @Test
    public void shouldCreateCourse() {
        List<Student> studentList = new ArrayList<>();
        Student student = new Student(1L, "João Cleber", "489.426.430-78", "25/02/2000");
        studentList.add(student);
        Course course = new Course(2L, "Curso de python", 50.0, 60, studentList);

        courseService.createCourse(course);

    }

    @Test
    public void shouldDeleteCourseIfExists() {
        Course course = new Course();
        course.setId(1L);

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        courseService.deleteCourse(1L);

        verify(courseRepository).deleteById(1L);
    }
}