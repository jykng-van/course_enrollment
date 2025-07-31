package com.jykng.enrollment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jykng.enrollment.model.Course;
import com.jykng.enrollment.model.Teacher;
import com.jykng.enrollment.repository.CourseRepository;
import com.jykng.enrollment.repository.TeacherRepository;

import jakarta.transaction.Transactional;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    CourseRepository courseRepository;

    public List<Teacher> getTeachers(){
        var teachers = teacherRepository.findAll();
        return teachers;
    }

    public Optional<Teacher> getTeacherById(long id){
        var teacher = teacherRepository.findById(id);
        return teacher;
    }

    @Transactional
    public void deleteTeacher(long id){
        courseRepository.removeTeacherById(id); //remove teacher from courses

        teacherRepository.deleteById(id);
    }

    @Transactional
    public Teacher createTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
    }
    @Transactional
    public Teacher updateTeacher(Teacher teacher, long id){
        teacher.setId(id);
        return teacherRepository.save(teacher);
    }
}
