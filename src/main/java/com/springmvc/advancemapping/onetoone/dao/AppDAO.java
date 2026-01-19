package com.springmvc.advancemapping.onetoone.dao;

import com.springmvc.advancemapping.onetoone.entity.Course;
import com.springmvc.advancemapping.onetoone.entity.Instructor;
import com.springmvc.advancemapping.onetoone.entity.InstructorDetail;

import java.util.*;

public interface AppDAO {
    void saveInstructor(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);

    // Additional methods bidirectional access from InstructorDetail can be added here
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);

    // Methods for course management
    Course findCourseById(int id);
    List<Course> findCoursesByInstructorId(int instructorId);
    Instructor findCoursesByInstructorIdJoinFetch(int instructorId);

    void updateInstructor(Instructor instructor);
}
