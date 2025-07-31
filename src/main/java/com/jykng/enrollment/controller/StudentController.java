package com.jykng.enrollment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jykng.enrollment.model.EnrollInput;
import com.jykng.enrollment.model.Student;
import com.jykng.enrollment.service.StudentService;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    // List all students
    @RequestMapping(value = "students", method = RequestMethod.GET)
    public List<Student> listStudents() {
        return studentService.getStudents();
    }

    // Get one student by id
    @RequestMapping(value = "students/{id}", method = RequestMethod.GET)
    public Optional<Student> getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // Create a new student
    @RequestMapping(value = "students", method = RequestMethod.POST)
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    // Update an existing student
    @RequestMapping(value = "students/{id}", method = RequestMethod.PUT)
    public Student updateStudent(@RequestBody Student student, @PathVariable Long id) {
        return studentService.updateStudent(student, id);
    }

    // Delete a student
    @RequestMapping(value = "students/{id}", method = RequestMethod.DELETE)
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student deleted";
    }

    // Enroll in a course
    @RequestMapping(value = "students/{id}", method = RequestMethod.PATCH)
    public Student enrollCourse(@PathVariable Long id, @RequestBody EnrollInput input){
        return studentService.enroll(id, input.getCourseId());
    }
}