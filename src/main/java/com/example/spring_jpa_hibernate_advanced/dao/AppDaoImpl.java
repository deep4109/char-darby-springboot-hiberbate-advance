package com.example.spring_jpa_hibernate_advanced.dao;

import com.example.spring_jpa_hibernate_advanced.entity.Instructor;
import com.example.spring_jpa_hibernate_advanced.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        return entityManager.find(Instructor.class,id);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {

        //retrive instrucor
        Instructor instructor = entityManager.find(Instructor.class,id);

        //delete
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {

        //retrive instrucor
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class,id);

        //remove teh asscaoited object and break bidirectional
        instructorDetail.getInstructor().setInstructorDetail(null);

        //delete
        entityManager.remove(instructorDetail);
    }
}
