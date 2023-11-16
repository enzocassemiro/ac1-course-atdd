package com.arula.Cursos.controllers;

import com.arula.Cursos.dto.CourseDTO;
import com.arula.Cursos.entity.Course;
import com.arula.Cursos.mapper.CourseMapper;
import com.arula.Cursos.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("v1")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody Course course) {
        CourseDTO courseDTO = CourseMapper.mapCourseToCourseDTO(courseService.createCourse(course));
        return new ResponseEntity<>(courseDTO, HttpStatus.CREATED);
    }

    @GetMapping("v1/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            CourseDTO courseDTO  = CourseMapper.mapCourseToCourseDTO(course.get());
            return new ResponseEntity<>(courseDTO, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("v1")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        List<CourseDTO> coursesDtos = courses.stream()
                .map(CourseMapper::mapCourseToCourseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(coursesDtos);
    }
}
