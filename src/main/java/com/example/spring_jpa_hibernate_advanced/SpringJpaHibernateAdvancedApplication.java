package com.example.spring_jpa_hibernate_advanced;

import com.example.spring_jpa_hibernate_advanced.dao.AppDao;
import com.example.spring_jpa_hibernate_advanced.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringJpaHibernateAdvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaHibernateAdvancedApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDao appDao) {
        return runner -> {
            System.out.println("Hello Hibernate Advance concepts");

//            for (int i = 0; i < 10; i++) {
//				createInstructor(appDao,1 + " Deep");
//            }
            //createInstructor(appDao);
            //getInstructorById(appDao);
            //deleteInstructorById(appDao);
            //getInstructorDetailById(appDao);
            //deleteInstructorDetailById(appDao);
            //createInstructorWithCourses(appDao);
            //findInstructorWithCourses(appDao);
            //findInstructorWithCoursesbyJoinFetch(appDao);
            //updateInstructor(appDao);
            // updateCourses(appDao);
            //deleteInstructor(appDao);
            deleteCourse(appDao);
            // createCourseAndReviews(appDao);
            // findCourseWithReviewbyJoinFetch(appDao);
            //deleteCourseAndReviews(appDao);
            //createCourseAndStudents(appDao);
            //findCourseAndStudents(appDao);
            //findStudentAndCourses(appDao);
           // addMoreCoursesForStudent(appDao);
        };
    }

    private void deleteStudent(AppDao appDAO) {

        int theId = 1;
        System.out.println("Deleting student id: " + theId);

        appDAO.deleteStudentById(theId);

        System.out.println("Done!");
    }

    private void addMoreCoursesForStudent(AppDao appDAO) {

        int theId = 2;
        Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

        // create more courses
        Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
        Course tempCourse2 = new Course("Atari 2600 - Game Development");

        // add courses to student
        tempStudent.addCourse(tempCourse1);
        tempStudent.addCourse(tempCourse2);

        System.out.println("Updating student: " + tempStudent);
        System.out.println("associated courses: " + tempStudent.getCourses());

        appDAO.update(tempStudent);

        System.out.println("Done!");
    }

    private void findStudentAndCourses(AppDao appDAO) {

        int theId = 2;
        Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

        System.out.println("Loaded student: " + tempStudent);
        System.out.println("Courses: " + tempStudent.getCourses());

        System.out.println("Done!");
    }

    private void findCourseAndStudents(AppDao appDAO) {

        int theId = 10;
        Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);

        System.out.println("Loaded course: " + tempCourse);
        System.out.println("Students: " + tempCourse.getStudents());

        System.out.println("Done!");
    }

    private void createCourseAndStudents(AppDao appDAO) {

        // create a course
        Course tempCourse = new Course("Pacman - How To Score One Million Points");

        // create the students
        Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
        Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");

        // add students to the course
        tempCourse.addStudent(tempStudent1);
        tempCourse.addStudent(tempStudent2);

        // save the course and associated students
        System.out.println("Saving the course: " + tempCourse);
        System.out.println("associated students: " + tempCourse.getStudents());

        appDAO.save(tempCourse);

        System.out.println("Done!");
    }


    private void deleteCourseAndReviews(AppDao appDao) {
        int theID = 10;
        System.out.println("Delete the course id: " + theID);
        appDao.deleteCourse(theID);
        System.out.println("Done");

    }

    private void findCourseWithReviewbyJoinFetch(AppDao appDao) {
        int theID = 10;

        //lazy loading
        Course courses = appDao.findCourseAndReviewsByCourseId(theID);
        System.out.println("couses: " + courses);

        System.out.println("reviews: " + courses.getReviews());

        System.out.println("Done!");
    }

    private void createCourseAndReviews(AppDao appDao) {
        Course course1 = new Course("Pacman2 - How to scrore poits");

        //add reviews
        course1.add(new Review("Amazing course"));
        course1.add(new Review("Good course"));
        course1.add(new Review("Better course"));
        course1.add(new Review("Best course"));


        // save course
        System.out.println("Saving Couse" + course1);
        appDao.save(course1);
        System.out.println("Done");
    }

    private void deleteInstructor(AppDao appDao) {
        int theID = 1;
        System.out.println("instructor the course id: " + theID);
        appDao.deleteInstructor(theID);
        System.out.println("Done");
    }

    private void deleteCourse(AppDao appDao) {
        int theID = 10;
        System.out.println("deleting the course id: " + theID);
        appDao.deleteCourse(theID);
        System.out.println("Done");
    }

    private void updateCourses(AppDao appDao) {
        int theID = 10;
        System.out.println("find the course id: " + theID);
        Course tempCourses = appDao.findCoursesById(theID);

        System.out.println("Updating the course id: " + theID);
        tempCourses.setTitle("Bharti");
        appDao.updateCourse(tempCourses);
        System.out.println("Done");
    }

    private void updateInstructor(AppDao appDao) {
        int theID = 1;
        System.out.println("find the instructor id: " + theID);
        Instructor tempInstructor = appDao.findInstructorById(theID);

        System.out.println("Updating the instructor id: " + theID);
        tempInstructor.setLastName("Bharti");
        appDao.update(tempInstructor);
        System.out.println("Done");
    }

    private void findInstructorWithCoursesbyJoinFetch(AppDao appDao) {
        int theID = 1;

        //lazy loading
        Instructor instructor = appDao.findInstructorByJoinFetch(1);
        System.out.println("instructor: " + instructor);

        System.out.println("courses: " + instructor.getCourses());

        System.out.println("Done!");

    }

    private void findInstructorWithCourses(AppDao appDao) {
        int theID = 1;

        Instructor instructor = appDao.findInstructorById(1);
        System.out.println("instructor: " + instructor);

        //lazy loading
        List<Course> courses = appDao.findCoursesByInstructorId(theID);

        //assocaiote object
        instructor.setCourses(courses);

        System.out.println("courses: " + instructor.getCourses());

        System.out.println("Done!");

    }

    private void createInstructorWithCourses(AppDao appDao) {
        Instructor tempInstructor = new Instructor("Chad", "Darby", "madhu@luv2code.com");

        InstructorDetail instructorDetail =
                new InstructorDetail("http://www.love2code.com/youtube", "Luv 2 code");

        tempInstructor.setInstructorDetail(instructorDetail);

        Course tempCourse1 = new Course("Guitar - the guide");
        Course tempCourse2 = new Course("Table Tennis - the guide");
        Course tempCourse3 = new Course("The Pinball  - the guide");
        Course tempCourse4 = new Course("The Baseball - the guide");

        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);
        tempInstructor.add(tempCourse3);
        tempInstructor.add(tempCourse4);


        //save the instructor
        System.out.println("Saving instructor" + tempInstructor);
        appDao.save(tempInstructor);
        System.out.println("Saved done");

    }

    private void deleteInstructorDetailById(AppDao appDao) {
        //delete
        int id = 4;
        appDao.deleteInstructorDetailById(id);
        System.out.println("Removed InstructorDetail");
    }

    private void deleteInstructorById(AppDao appDao) {
        //delete
        int id = 1;
        appDao.deleteInstructorById(id);
        System.out.println("Removed instructor");
    }

    private void getInstructorDetailById(AppDao appDao) {
        //get the instructor
        int id = 2;
        InstructorDetail instructor = appDao.findInstructorDetailById(id);
        System.out.println("InstructorDetail" + instructor);
        System.out.println("Instructor" + instructor.getInstructor());
    }

    private void getInstructorById(AppDao appDao) {
        //get the instructor
        Instructor instructor = appDao.findInstructorById(1);
        System.out.println("Get instructor" + instructor);
    }

    private void createInstructor(AppDao appDao) {

        Instructor tempInstructor = new Instructor("Chad", "Darby", "madhu@luv2code.com");

        InstructorDetail instructorDetail =
                new InstructorDetail("http://www.love2code.com/youtube", "Luv 2 code");

        tempInstructor.setInstructorDetail(instructorDetail);

        //save the instructor
        System.out.println("Saving instructor" + tempInstructor);
        appDao.save(tempInstructor);
        System.out.println("Saved done");
    }

    private void createInstructor(AppDao appDao, String name) {

        Instructor tempInstructor = new Instructor("madhu@luv2code.com", "Darby", name);

        InstructorDetail instructorDetail =
                new InstructorDetail("http://www.love2code.com/youtube", "Luv 2 code");

        tempInstructor.setInstructorDetail(instructorDetail);

        //save the instructor
        System.out.println("Saving instructor" + tempInstructor);
        appDao.save(tempInstructor);
        System.out.println("Saved done");
    }

}
