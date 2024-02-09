package org.example.homework.four.service;


import lombok.Data;
import org.example.homework.four.exceptions.CourseNotFoundException;
import org.example.homework.four.exceptions.ExistingRegistrationNumberException;
import org.example.homework.four.exceptions.InvalidDurationException;
import org.example.homework.four.exceptions.InvalidRegistrationNumberException;
import org.example.homework.four.model.Course;
import org.example.homework.four.dao.CoursesDAOImpl;

import java.util.List;

@Data
public class CourseService {

    private final static String REGISTRATION_NUMBER_ALREADY_EXISTS = "The specified registration number already exists in the database";
    private final static String COURSE_NOT_FOUND = "The specified student could not be found";
    private final static String REGISTRATION_NUMBER_INVALID_LENGTH = "The registration number must have exactly 4 digits.";
    private final static String INVALID_DURATION = "The student must be 13 years or older to be registered";

    private final CoursesDAOImpl coursesDAO;

    public CourseService(){
        this.coursesDAO = new CoursesDAOImpl();
    }

    /**
     * Inserts a course in the database
     * @param course to be added to the database.
     */
    public boolean saveCourse(Course course) throws ExistingRegistrationNumberException, InvalidRegistrationNumberException,
            InvalidDurationException
    {
        if(coursesDAO.listOfCourses().stream()
                .anyMatch(c -> c.getRegistrationNumber().equals(course.getRegistrationNumber())))
            throw new ExistingRegistrationNumberException(REGISTRATION_NUMBER_ALREADY_EXISTS);
        if(course.getRegistrationNumber().length() > 5)
            throw new InvalidRegistrationNumberException(REGISTRATION_NUMBER_INVALID_LENGTH);
        if(course.getDuration() > 10.0)
            throw new InvalidDurationException(INVALID_DURATION);

        return this.getCoursesDAO().save(course);
    }

    /**
     * Search a course in the database
     * @param id of the course to be searched.
     * @return course with the specified ID.
     */
    public Course findById(int id) throws CourseNotFoundException{

        Course course = coursesDAO.findById(id,Course.class);
        if(course == null)
            throw new CourseNotFoundException(COURSE_NOT_FOUND);

        return course;
    }


    /**
     * List all courses.
     * @return a list with all courses in the database.
     */
    public List<Course> getCourseList(){

        return this.getCoursesDAO().listOfCourses();
    }

    /**
     * @param id of the course to be removed from the database.
     * @return true if the course exists, false otherwise.
     */
    public boolean removeCourse(int id) throws CourseNotFoundException{

        Course foundCourse = getCoursesDAO().findById(id,Course.class);
        if(foundCourse == null)
            throw new CourseNotFoundException(COURSE_NOT_FOUND);

        return this.getCoursesDAO().remove(foundCourse);
    }

    /**
     *
     * @param course to be updated.
     * @return true if the course exists in the database and the update was successful, false otherwise.
     */
    public boolean updateCourse(Course course) throws CourseNotFoundException{

        if(!getCoursesDAO().listOfCourses().contains(course))
            throw new CourseNotFoundException(COURSE_NOT_FOUND);

        return this.getCoursesDAO().update(course);
    }

}
