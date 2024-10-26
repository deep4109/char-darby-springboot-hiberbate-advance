package com.example.spring_jpa_hibernate_advanced.dao;

import com.example.spring_jpa_hibernate_advanced.entity.Course;
import com.example.spring_jpa_hibernate_advanced.entity.Instructor;
import com.example.spring_jpa_hibernate_advanced.entity.InstructorDetail;
import com.example.spring_jpa_hibernate_advanced.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDaoImpl implements AppDao {

    private final EntityManager entityManager;

    @Autowired
    public AppDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    public Instructor findInstructorByJoinFetch(int id) {
        //create query
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i "
                + "JOIN FETCH i.courses " + "where i.id = :data", Instructor.class);
        query.setParameter("data", id);

        //execute query
        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        //create query
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data", id);

        //execute query
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {

        //retrive instrucor
        Instructor instructor = entityManager.find(Instructor.class, id);

        //delete
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {

        //retrive instrucor
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);

        //remove teh asscaoited object and break bidirectional
        instructorDetail.getInstructor().setInstructorDetail(null);

        //delete
        entityManager.remove(instructorDetail);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCoursesById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteCourse(int id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void deleteInstructor(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        List<Course> courseList = instructor.getCourses();
        for (Course tempCOurse:courseList){
            tempCOurse.setInstructor(null);
        }
        instructor.setCourses(null);
        entityManager.remove(instructor);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        //create query
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c "
                + "JOIN FETCH c.reviews " + "where c.id = :data", Course.class);
        query.setParameter("data", id);

        //execute query
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.students "
                        + "where c.id = :data", Course.class);

        query.setParameter("data", theId);

        // execute query
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {
        // create query
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s "
                        + "JOIN FETCH s.courses "
                        + "where s.id = :data", Student.class);

        query.setParameter("data", theId);

        // execute query
        Student student = query.getSingleResult();

        return student;
    }

    @Override
    @Transactional
    public void update(Student tempStudent) {
        entityManager.merge(tempStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        // retrieve the student
        Student tempStudent = entityManager.find(Student.class, theId);

        // delete the student
        entityManager.remove(tempStudent);
    }
}
