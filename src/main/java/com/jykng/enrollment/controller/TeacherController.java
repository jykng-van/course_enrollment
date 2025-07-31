package com.jykng.enrollment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jykng.enrollment.model.Teacher;
import com.jykng.enrollment.service.TeacherService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@RestController
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    //TeacherRepository teacherRepository;

    //List Teachers
    @RequestMapping(value="teachers", method=RequestMethod.GET)
    public List<Teacher> listTeachers() {
        return teacherService.getTeachers();
    }

    //Get one Teacher by id
    @RequestMapping(value="teachers/{id}", method=RequestMethod.GET)
    public Optional<Teacher> getTeacher(@PathVariable long id) {
        return teacherService.getTeacherById(id);
    }

    //Delete Teacher
    @RequestMapping(value="teachers/{id}", method=RequestMethod.DELETE)
    public String deleteTeacher(@PathVariable long id){
        teacherService.deleteTeacher(id);
        return "Teacher deleted";
    }

    //Create Teacher
    @RequestMapping(value="teachers", method=RequestMethod.POST)
    public Teacher createTeacher(@RequestBody Teacher teacher){
        return teacherService.createTeacher(teacher);
    }

    //Update Teacher by id
    @RequestMapping(value="teachers/{id}", method=RequestMethod.PUT)
    public Teacher updateTeacher(@RequestBody Teacher teacher, @PathVariable long id) {
        return teacherService.updateTeacher(teacher, id);
    }

}
