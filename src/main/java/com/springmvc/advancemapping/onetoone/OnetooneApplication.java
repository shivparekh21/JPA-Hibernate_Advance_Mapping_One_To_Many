package com.springmvc.advancemapping.onetoone;

import com.springmvc.advancemapping.onetoone.dao.AppDAO;
import com.springmvc.advancemapping.onetoone.entity.Course;
import com.springmvc.advancemapping.onetoone.entity.Instructor;
import com.springmvc.advancemapping.onetoone.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnetooneApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnetooneApplication.class, args);
	}

	// commandLineRunner method are Excuted after the spring beans are loaded
	// no need for @Autowired here as it is annotated with @Bean
	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
//			createInstructor(appDAO);
//			findInstructorById(appDAO);
//			deleteInstructorById(appDAO);

			// one to one bidirectional

//			findInstructorDetailById(appDAO);
//			deleteInstructorDetailById(appDAO);

			//
//			createInstructorWithCources(appDAO);
//			findInstructorByCoursesId(appDAO);
			findInstructorWithCourses(appDAO);
		};
	}


	private void deleteInstructorById(AppDAO appDAO) {
		int theId = 2;
		System.out.println("Deleting instructor id: " + theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Deleted instructor id: " + theId);
	}

	private void findInstructorById(AppDAO appDAO) {
		int theId = 3;
		Instructor instructor = appDAO.findInstructorById(theId);
		System.out.println("Found instructor: " + instructor);
	}

	private void createInstructor(AppDAO appDAO) {

		// create the instructor
		Instructor instructor =
				new Instructor("Rahul", "Patel", "r.patel@gmail.com");

		// create the instructor detail
		InstructorDetail instructorDetail =
				new InstructorDetail("http://www.youtube.com/johndoe", "Golf");

		// associate the objects
		instructor.setInstructorDetail(instructorDetail);

		// save the instructor
		System.out.println("Saving instructor: " + instructor);
		appDAO.saveInstructor(instructor);
	}

// InstructorDetail classes

	private void findInstructorDetailById(AppDAO appDAO) {
		int theId = 1;
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(theId);
		System.out.println("Found instructor detail: " + instructorDetail);

		// also print the associated instructor
		System.out.println("The associated instructor: " + instructorDetail.getInstructor());
	}

	private void deleteInstructorDetailById(AppDAO appDAO) {
		int theId=5;
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Deleted instructor detail id: " + theId);
	}





	private void createInstructorWithCources(AppDAO appDAO) {
		Instructor instructor =
				new Instructor("Susan", "Public", "	susan.p@gmail.com");
		InstructorDetail instructorDetail =
				new InstructorDetail("http://www.youtube.com/susanpublic", "Painting");
		instructor.setInstructorDetail(instructorDetail);
		// create some courses
		Course tempCourse1 = new Course("Painting - The Ultimate Guide");
		Course tempCourse2 = new Course("Sketching for Beginners");
		// add courses to instructor
		instructor.addCourse(tempCourse1);
		instructor.addCourse(tempCourse2);
		// save the instructor
		System.out.println("Saving instructor: " + instructor);
		appDAO.saveInstructor(instructor);
	}

	private void findInstructorByCoursesId(AppDAO appDAO) {
		int courseId = 10;
		Course course = appDAO.findCourseById(courseId);
		Instructor courseInstructor = course.getInstructor();
		System.out.println("Course: " + course);
		System.out.println("Instructor of the course: " + courseInstructor);
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;
		Instructor instructor = appDAO.findInstructorById(theId);
		System.out.println("Instructor: " + instructor);
		System.out.println("Courses: " + instructor.getCourse());
	}

}
