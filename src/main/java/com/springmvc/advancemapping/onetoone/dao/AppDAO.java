package com.springmvc.advancemapping.onetoone.dao;

import com.springmvc.advancemapping.onetoone.entity.Course;
import com.springmvc.advancemapping.onetoone.entity.Instructor;
import com.springmvc.advancemapping.onetoone.entity.InstructorDetail;

public interface AppDAO {
    void saveInstructor(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);

    // Additional methods bidirectional access from InstructorDetail can be added here
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);

    // Methods for course management
    Course findCourseById(int id);
}
