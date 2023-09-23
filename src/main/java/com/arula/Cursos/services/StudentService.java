package com.arula.Cursos.services;

import com.arula.Cursos.entity.Course;
import com.arula.Cursos.entity.Student;
import com.arula.Cursos.exceptions.CourseIsFullException;
import com.arula.Cursos.exceptions.NotFoundException;
import com.arula.Cursos.exceptions.StudentNotEnrolledException;
import com.arula.Cursos.repositories.CourseRepository;
import com.arula.Cursos.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> getStudentByNationalId(String nationalId) {
        return studentRepository.findByNationalId(nationalId);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        if (studentRepository.existsById(id)) {
            updatedStudent.setId(id);
            return studentRepository.save(updatedStudent);
        } else {
            throw new NotFoundException(id);
        }
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student addCourseToStudent(Long studentId, Course course) throws CourseIsFullException {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            List<Course> studentCourses = student.getCourses();

            if (course.getAvailableSpots() > 0) {
                if (!studentCourses.contains(course)) {
                    studentCourses.add(course);
                    studentRepository.save(student);
                }
                return student;
            } else {
                throw new CourseIsFullException("Course is full");
            }
        } else {
            throw new NotFoundException(studentId);
        }
    }

    public Student removeCourseFromStudent(Long studentId, Long courseId) throws StudentNotEnrolledException {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Optional<Course> optionalCourse = courseRepository.findById(courseId);

        if (optionalStudent.isPresent() && optionalCourse.isPresent()) {
            Student student = optionalStudent.get();
            Course course = optionalCourse.get();
            List<Course> studentCourses = student.getCourses();

            if (studentCourses.contains(course)) {
                studentCourses.remove(course);
                studentRepository.save(student);
            } else {
                throw new StudentNotEnrolledException("Student is not enrolled in course");
            }
            return student;
        } else {
            throw new NotFoundException(studentId, courseId);
        }
    }

    public List<Course> getAllCoursesOfStudent(Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            return student.getCourses();
        } else {
            throw new NotFoundException(studentId);
        }
    }
}
