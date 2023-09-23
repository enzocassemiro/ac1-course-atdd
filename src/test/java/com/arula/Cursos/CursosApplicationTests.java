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

	@Test
	void shouldValidateThatUserViewsTheirCourses() {

		List<Course> courses = generateRandomCourses(1);
		Student student = new Student("Cleber Castro", "489.426.430-78", "02/04/1999");
		student.addCourse(courses.get(0));

		boolean canViewCourse = courses.get(0).validateStudentOnCourse(student);

		assertTrue(canViewCourse);
	}

	@Test
	void shouldValidateThatUserCannotViewsOtherCourses() {
		List<Student> students = new ArrayList<>();
		Student student = new Student("Cleber Castro", "489.426.430-78", "02/04/1999");
		students.add(student);
		Course course = new Course(1L, "Curso de tecnologia", 80.0, 70, students);

		boolean canViewCourse = course.validateStudentOnCourse(student);

		assertFalse(canViewCourse);
	}

	@Test
	void shouldThrowErrorIfDefaultStudentExceedsLimit() {
		List<Course> courses = generateRandomCourses(4);
		Student student = new Student("Cleber Castro", "489.426.430-78", "02/04/1999");

		for (Course course : courses) {
			student.addCourse(course);
		}

		assertThrows(RuntimeException.class, () -> student.addCourse(courses.get(4)));
	}

	@Test
	void shouldThrowErrorIfPremiumStudentExceedsLimit() {

		List<Course> courses = generateRandomCourses(21);
		Student student = new Student("Cleber Castro", "489.426.430-78", "02/04/1999", StudentLevel.PREMIUM.getLimit());

		for (Course course : courses) {
			student.addCourse(course);
		}

		assertThrows(RuntimeException.class, () -> student.addCourse(courses.get(21)));
	}

	@Test
	void shouldAddedMultipleCoursesIfPremiumStudent() {

		List<Course> courses = generateRandomCourses(350);
		Student student = new Student("Cleber Castro", "489.426.430-78", "02/04/1999", StudentLevel.MENTOR.getLimit());

		for (Course course : courses) {
			student.addCourse(course);
		}

		assertEquals(student.getCourses().size(), 350);
	}

	private List<Course> generateRandomCourses(int numCourses) {
		List<Student> students = new ArrayList<>();
		List<Course> courses = new ArrayList<>();

		for (int i = 0; i < numCourses; i++) {
			String name = "Course " + i;
			double duration = new Random().nextDouble(50) + 10;
			int studentsEnrolled = new Random().nextInt(1000) + 1;
			Course course = new Course(1L, name, duration, studentsEnrolled, students);
			courses.add(course);
		}

		return courses;
	}
}