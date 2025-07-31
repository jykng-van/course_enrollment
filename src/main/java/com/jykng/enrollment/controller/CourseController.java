package com.jykng.enrollment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jykng.enrollment.model.Course;
import com.jykng.enrollment.model.CourseInput;
import com.jykng.enrollment.service.CourseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class CourseController {
    @Autowired
    CourseService courseService;

    // List all courses
    @RequestMapping(value = "courses", method = RequestMethod.GET)
    public List<Course> listCourses(@RequestParam String search) {
        return courseService.getCourses(search);
    }

    // List future courses
    @RequestMapping(value = "courses/future/{studentId}", method=RequestMethod.GET)
    public List<Course> futureCourses(@PathVariable Long studentId) {
        return courseService.getFutureCourses(studentId);
    }


    // Get one course by id
    @RequestMapping(value = "courses/{id}", method = RequestMethod.GET)
    public Optional<Course> getCourse(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    // Create a new course
    @RequestMapping(value = "courses", method = RequestMethod.POST)
    public Course createCourse(@RequestBody CourseInput course) {
        return courseService.createCourse(course);
    }

    // Update an existing course
    @RequestMapping(value = "courses/{id}", method = RequestMethod.PUT)
    public Course updateCourse(@RequestBody CourseInput course, @PathVariable Long id) {
        return courseService.updateCourse(course, id);
    }

    // Delete a course
    @RequestMapping(value = "courses/{id}", method = RequestMethod.DELETE)
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "Course deleted";
    }
}