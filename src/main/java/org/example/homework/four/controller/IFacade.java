package org.example.homework.four.controller;

import org.example.homework.four.exceptions.CourseNotFoundException;
import org.example.homework.four.exceptions.ExistingRegistrationNumberException;
import org.example.homework.four.exceptions.InvalidDurationException;
import org.example.homework.four.exceptions.InvalidRegistrationNumberException;
import org.example.homework.four.model.Course;

import java.util.List;

public interface IFacade {

    /**
     * Inserts a course in the database
     * @param course to be added to the database.
     */
    boolean saveCourse(Course course) throws ExistingRegistrationNumberException, InvalidRegistrationNumberException, InvalidDurationException;



    /**
     * Search a course in the database
     * @param id of the course to be searched.
     * @return course with the specified ID, null otherwise.
     */
    Course findById(int id) throws CourseNotFoundException;

    /**
     * List all courses.
     * @return a list with all courses in the database.
     */
    List<Course> listOfAllCourses();


    /**
     * @param id of the course to be removed from the database.
     * @return true if the course exists, false otherwise.
     */
    boolean removeCourse(int id) throws CourseNotFoundException;


    /**
     * @param course to be updated.
     * @return true if the course exists in the database and the update was successful, false otherwise.
     */
    boolean updateCourse(Course course) throws CourseNotFoundException;
}
