package com.jykng.enrollment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jykng.enrollment.model.Student;
import com.jykng.enrollment.model.StudentCourse;
import com.jykng.enrollment.model.Teacher;
import com.jykng.enrollment.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<Student> getStudents(){
        var students = studentRepository.findAll();
        return students;
    }
    public Optional<Student> getStudentById(long id){
        var student = studentRepository.findById(id);
        return student;
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }
    public Student updateStudent(Student student, long id){
        student.setId(id);
        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(long id){
        studentRepository.deleteStudentCourses(id);

        studentRepository.deleteById(id);
    }

    @Transactional
    public Student enroll(long student_id, long course_id){
        studentRepository.enrollCourse(student_id, course_id);

        return studentRepository.findById(student_id).get();
    }

}
