package com.example.spring_jpa_hibernate_advanced.dao;

import com.example.spring_jpa_hibernate_advanced.entity.Course;
import com.example.spring_jpa_hibernate_advanced.entity.Instructor;
import com.example.spring_jpa_hibernate_advanced.entity.InstructorDetail;
import com.example.spring_jpa_hibernate_advanced.entity.Student;

import java.util.List;

public interface AppDao {

    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    Instructor findInstructorByJoinFetch(int id);

    List<Course> findCoursesByInstructorId(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorById(int id);

    void deleteInstructorDetailById(int id);

    void save(Course course);

    void update(Instructor tempInstructor);

    void updateCourse(Course course);

    Course findCoursesById(int id);

    void deleteCourse(int id);

    void deleteInstructor(int id);

    Course findCourseAndReviewsByCourseId(int id);

    Course findCourseAndStudentsByCourseId(int theId);

    Student findStudentAndCoursesByStudentId(int theId);

    void update(Student tempStudent);

    void deleteStudentById(int theId);
}
