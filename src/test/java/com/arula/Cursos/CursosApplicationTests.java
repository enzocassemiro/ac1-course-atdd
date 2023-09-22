package com.arula.Cursos;

import com.arula.Cursos.entity.Course;
import com.arula.Cursos.entity.Student;
import com.arula.Cursos.enums.StudentLevel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


class CursosApplicationTests {

	private List<Course> generateRandomCourses(int numCourses) {
		List<Course> courses = new ArrayList<>();
		for (int i = 0; i < numCourses; i++) {
			String name = "Course " + i;
			int duration = new Random().nextInt(50) + 10; // duration between 10 and 50
			int studentsEnrolled = new Random().nextInt(1000) + 1; // students enrolled between 1 and 1000
			Course course = new Course(name, duration, studentsEnrolled);
			courses.add(course);
		}
		return courses;
	}

	@Test
	public void shouldValidateThatUserViewsTheirCourses() {
		List<Course> courses = generateRandomCourses(1);
		Student student = new Student("Cleber Castro", "489.426.430-78", "02/04/1999");
		student.addCourse(courses.get(0));
		assertTrue(courses.get(0).validateStudentOnCourse(student));
	}

	@Test
	public void shouldValidateThatUserCannotViewsOtherCourses() {
		Course course = new Course("Curso de tecnologia", 80,70);
		Student student = new Student("Cleber Castro", "489.426.430-78", "02/04/1999");
		assertFalse(course.validateStudentOnCourse(student));
	}

	@Test
	public void shouldThrowErrorIfDefaultStudentExceedsLimit() {
		List<Course> courses = generateRandomCourses(4);

		Student student = new Student("Cleber Castro", "489.426.430-78", "02/04/1999");
		for (int i = 0; i < courses.size(); i++) {
			if (i == 3) {
				break;
			}
			Course course = courses.get(i);
			student.addCourse(course);
		}
		assertThrows(RuntimeException.class, () -> student.addCourse(courses.get(4)));
	}

	@Test
	public void shouldThrowErrorIfPremiumStudentExceedsLimit() {
		List<Course> courses = generateRandomCourses(21);

		Student student = new Student("Cleber Castro", "489.426.430-78", "02/04/1999", StudentLevel.PREMIUM.getLimit());
		for (int i = 0; i < courses.size(); i++) {
			if (i == 20) {
				break;
			}
			Course course = courses.get(i);
			student.addCourse(course);
		}
		assertThrows(RuntimeException.class, () -> student.addCourse(courses.get(21)));
	}

	@Test
	public void shouldAddedMultipleCoursesIfPremiumStudent() {
		List<Course> courses = generateRandomCourses(350);

		Student student = new Student("Cleber Castro", "489.426.430-78", "02/04/1999", StudentLevel.MENTOR.getLimit());
		for (Course course : courses) {
			student.addCourse(course);
		}
		assertEquals(student.getCourses().size(), 350);
	}

}
