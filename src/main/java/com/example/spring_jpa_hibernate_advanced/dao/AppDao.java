package com.example.spring_jpa_hibernate_advanced.dao;

import com.example.spring_jpa_hibernate_advanced.entity.Instructor;
import com.example.spring_jpa_hibernate_advanced.entity.InstructorDetail;

public interface AppDao {

    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorById(int id);

    void deleteInstructorDetailById(int id);
}
