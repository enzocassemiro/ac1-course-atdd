package com.arula.Cursos.services;

import com.arula.Cursos.entity.Course;
import com.arula.Cursos.entity.Student;
import com.arula.Cursos.exceptions.CourseIsFullException;
import com.arula.Cursos.exceptions.StudentNotEnrolledException;
import com.arula.Cursos.repositories.CourseRepository;
import com.arula.Cursos.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        studentService = new StudentService(studentRepository, courseRepository);
    }

    @Test
    void shouldCreateStudent() {
        Student student = new Student(1L, "João Cleber", "489.426.430-78", "25/02/2000");
        studentService.createStudent(student);
    }

    @Test
    void shouldDeleteStudent() {
        Student student = new Student(1L, "João Cleber", "489.426.430-78", "25/02/2000");
        studentService.createStudent(student);
        studentService.deleteStudent(1L);
    }

    @Test
    void shouldAddCourseToStudentIfCourseIsNotFull() throws CourseIsFullException {
        List<Student> studentList = new ArrayList<>();
        Student student = new Student(1L, "João Cleber", "489.426.430-78", "25/02/2000");
        studentList.add(student);
        Course course = new Course(2L, "Curso de python", 50.0, 10, studentList);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findById(2L)).thenReturn(Optional.of(course));
        Student updatedStudent = studentService.addCourseToStudent(1L, course);

        assertTrue(updatedStudent.getCourses().contains(course));
    }

    @Test
    void shouldThrowCourseIsFullExceptionIfCourseIsFull() {
        List<Student> studentList = new ArrayList<>();
        Student student = new Student(1L, "João Cleber", "489.426.430-78", "25/02/2000");
        studentList.add(student);
        Course course = new Course(2L, "Curso de python", 50.0, 60, studentList);
        course.setAvailableSpots(0);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findById(2L)).thenReturn(Optional.of(course));

        assertThrows(CourseIsFullException.class, () -> studentService.addCourseToStudent(1L, course));
    }

    @Test
    void shouldRemoveCourseFromStudent() throws StudentNotEnrolledException {
        List<Student> studentList = new ArrayList<>();
        Student student = new Student(1L, "João Cleber", "489.426.430-78", "25/02/2000");
        studentList.add(student);
        Course course = new Course(2L, "Curso de python", 50.0, 60, studentList);
        course.setAvailableSpots(0);
        student.getCourses().add(course);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findById(2L)).thenReturn(Optional.of(course));
        Student updatedStudent = studentService.removeCourseFromStudent(1L, 2L);

        assertFalse(updatedStudent.getCourses().contains(course));
    }

    @Test
    void shouldThrowStudentNotEnrolledException() {
        List<Student> studentList = new ArrayList<>();
        Student student = new Student(1L, "João Cleber", "489.426.430-78", "25/02/2000");
        studentList.add(student);
        Course course = new Course(2L, "Curso de python", 50.0, 60, studentList);
        course.setAvailableSpots(0);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findById(2L)).thenReturn(Optional.of(course));

        assertThrows(StudentNotEnrolledException.class, () -> studentService.removeCourseFromStudent(1L, 2L));
    }

    @Test
    void shouldListAllCoursesOfStudent() {
        List<Student> studentList = new ArrayList<>();
        Student student = new Student(1L, "João Cleber", "489.426.430-78", "25/02/2000");
        studentList.add(student);
        Course course = new Course(2L, "Curso de python", 50.0, 60, studentList);
        Course course2 = new Course(3L, "Curso de java", 80.0, 20, studentList);

        student.getCourses().add(course);
        student.getCourses().add(course2);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        List<Course> courses = studentService.getAllCoursesOfStudent(1L);

        assertEquals(2, courses.size());
        assertTrue(courses.contains(course));
        assertTrue(courses.contains(course2));
    }
}
