package com.arula.Cursos.services;

import com.arula.Cursos.entity.Course;
import com.arula.Cursos.exceptions.NotFoundException;
import com.arula.Cursos.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course updatedCourse) {
        if (courseRepository.existsById(id)) {
            updatedCourse.setId(id);
            return courseRepository.save(updatedCourse);
        } else {
            throw new NotFoundException(id);
        }
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
