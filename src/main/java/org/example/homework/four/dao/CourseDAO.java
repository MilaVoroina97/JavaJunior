package org.example.homework.four.service.dao;

import org.example.homework.four.model.Course;

import java.util.List;

public abstract class CourseDAO extends GenericDAO<Course>{

    public abstract List<Course> listOfCourses();

}
