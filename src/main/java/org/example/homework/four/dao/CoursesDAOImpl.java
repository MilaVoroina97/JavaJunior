package org.example.homework.four.service.dao;

import org.example.homework.four.model.Course;

import java.util.List;

public class CoursesDAOImpl extends CourseDAO{
    @Override
    public List<Course> listOfCourses() {
        return getEntityManager().createQuery("SELECT c FROM Course c", Course.class).getResultList();
    }
}
