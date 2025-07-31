package com.jykng.enrollment.service;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jykng.enrollment.model.Course;
import com.jykng.enrollment.model.CourseInput;
import com.jykng.enrollment.repository.CourseRepository;
import com.jykng.enrollment.repository.SubjectRepository;
import com.jykng.enrollment.repository.TeacherRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @PersistenceContext
    EntityManager entityManager;

    public List<Course> getCourses(String search) {
        if (search == null || search.isEmpty()){
            return courseRepository.findAll();
        }else{
            search = search.toLowerCase();
            return courseRepository.findAllWithSearch(search); //lowercase for case insensitivity with query
        }

    }

    public Optional<Course> getCourseById(long id) {
        return courseRepository.findById(id);
    }

    @Transactional
    public Course createCourse(CourseInput input) {
        Course course = new Course();
        course.setSubject(subjectRepository.findById(input.getSubjectId()).get());
        course.setTeacher(teacherRepository.findById(input.getTeacherId()).get());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        try {
            Date start_date = formatter.parse(input.getStartDate());
            Date end_date = formatter.parse(input.getEndDate());

            course.setStartDate(start_date);
            course.setEndDate(end_date);
        } catch (java.text.ParseException e) {
            throw new RuntimeException("Invalid date format", e);
        }

        return courseRepository.save(course);
    }

    @Transactional
    public Course updateCourse(CourseInput input, long id) {
        courseRepository.updateCourse(input.getSubjectId(), input.getTeacherId(), input.getStartDate(), input.getEndDate(), id);
        var course = courseRepository.findById(id);
        return course.get();
    }

    @Transactional
    public void deleteCourse(long id) {
        courseRepository.deleteStudentCourses(id);
        courseRepository.reallyDeleteById(id); //Too many eager fetches prevent just plain deleteById from working
    }

    public List<Course> getFutureCourses(long studentId){
        return courseRepository.getFutureCourses(studentId);
    }
}