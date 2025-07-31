package com.jykng.enrollment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jykng.enrollment.model.Subject;
import com.jykng.enrollment.repository.CourseRepository;
import com.jykng.enrollment.repository.SubjectRepository;

import jakarta.transaction.Transactional;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    CourseRepository courseRepository;

    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(long id) {
        return subjectRepository.findById(id);
    }

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject updateSubject(Subject subject, long id) {
        subject.setId(id);
        return subjectRepository.save(subject);
    }

    @Transactional
    public void deleteSubject(long id) {
        //delete relations
        subjectRepository.deleteStudentCourses(id);
        courseRepository.deleteBySubjectId(id);

        subjectRepository.deleteById(id);
    }
}
