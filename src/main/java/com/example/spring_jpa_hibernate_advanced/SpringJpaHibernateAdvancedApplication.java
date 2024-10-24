package com.example.spring_jpa_hibernate_advanced;

import com.example.spring_jpa_hibernate_advanced.dao.AppDao;
import com.example.spring_jpa_hibernate_advanced.entity.Instructor;
import com.example.spring_jpa_hibernate_advanced.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
            deleteInstructorDetailById(appDao);
        };
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

	private void createInstructor(AppDao appDao,String name) {

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
