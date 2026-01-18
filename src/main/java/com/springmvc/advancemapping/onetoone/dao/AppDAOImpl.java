package com.springmvc.advancemapping.onetoone.dao;


import com.springmvc.advancemapping.onetoone.entity.Course;
import com.springmvc.advancemapping.onetoone.entity.Instructor;
import com.springmvc.advancemapping.onetoone.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AppDAOImpl implements AppDAO {

    private EntityManager theEntityManager;

    @Autowired
    public AppDAOImpl(EntityManager theEntityManager) {
        this.theEntityManager = theEntityManager;
    }



    @Override
    @Transactional
    public void saveInstructor(Instructor instructor) {
        theEntityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        Instructor theInstructor = theEntityManager.find(Instructor.class, id);
        if (theInstructor == null) {
            System.out.println("Instructor not found with id: " + id);
            return null;
        } else {
            return theInstructor;
        }
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = findInstructorById(id);
        if (instructor != null) {
            theEntityManager.remove(instructor);
        }
    }


    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return theEntityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = findInstructorDetailById(id);
        if (instructorDetail != null) {
            instructorDetail.getInstructor().setInstructorDetail(null);
            theEntityManager.remove(instructorDetail);
        }else {
            System.out.println("InstructorDetail not found with id: " + id);
        }
    }

    @Override
    public Course findCourseById(int id) {
        return theEntityManager.find(Course.class, id);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int instructorId) {

        TypedQuery<Course> tempQuery = theEntityManager.createQuery("from Course where instructor.id=:data", Course.class);
        tempQuery.setParameter("data", instructorId);
        List<Course> courses = tempQuery.getResultList();
        return courses;
    }

    @Override
    public Instructor findCoursesByInstructorIdJoinFetch(int instructorId) {
        TypedQuery<Instructor> tempQuery = theEntityManager.createQuery(
                                        "SELECT i FROM Instructor i "
                                                     + "JOIN FETCH i.courses "
                                                     + "WHERE i.id=:data", Instructor.class);
        tempQuery.setParameter("data", instructorId);
        Instructor instructor = tempQuery.getSingleResult();
        return instructor;
    }
}
