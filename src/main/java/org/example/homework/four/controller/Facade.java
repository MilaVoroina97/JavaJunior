package org.example.homework.four.controller;

import org.example.homework.four.exceptions.CourseNotFoundException;
import org.example.homework.four.exceptions.ExistingRegistrationNumberException;
import org.example.homework.four.exceptions.InvalidDurationException;
import org.example.homework.four.exceptions.InvalidRegistrationNumberException;
import org.example.homework.four.model.Course;
import org.example.homework.four.service.CourseService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class Facade implements IFacade{


    private final CourseService courseService = new CourseService();

    @Override
    public boolean saveCourse(Course course) throws ExistingRegistrationNumberException, InvalidRegistrationNumberException, InvalidDurationException {
        return courseService.saveCourse(course);
    }

    @Override
    public Course findById(int id) throws CourseNotFoundException {
        return courseService.findById(id);
    }

    @Override
    public List<Course> listOfAllCourses() {
        return courseService.getCourseList();
    }

    @Override
    public boolean removeCourse(int id) throws CourseNotFoundException {
        return courseService.removeCourse(id);
    }

    @Override
    public boolean updateCourse(Course course) throws CourseNotFoundException {
        return courseService.updateCourse(course);
    }
}
