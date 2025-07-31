package com.jykng.enrollment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jykng.enrollment.model.Subject;
import com.jykng.enrollment.repository.SubjectRepository;
import com.jykng.enrollment.service.SubjectService;

@RestController
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    // List all subjects
    @RequestMapping(value = "subjects", method = RequestMethod.GET)
    public List<Subject> listSubjects() {
        return subjectService.getSubjects();
    }

    // Get one subject by id
    @RequestMapping(value = "subjects/{id}", method = RequestMethod.GET)
    public Optional<Subject> getSubject(@PathVariable Long id) {
        return subjectService.getSubjectById(id);
    }

    // Create a new subject
    @RequestMapping(value = "subjects", method = RequestMethod.POST)
    public Subject createSubject(@RequestBody Subject subject) {
        return subjectService.createSubject(subject);
    }

    // Update an existing subject
    @RequestMapping(value = "subjects/{id}", method = RequestMethod.PUT)
    public Subject updateSubject(@RequestBody Subject subject, @PathVariable Long id) {
        return subjectService.updateSubject(subject, id);
    }

    // Delete a subject
    @RequestMapping(value = "subjects/{id}", method = RequestMethod.DELETE)
    public String deleteSubject(@PathVariable Long id) {


        subjectService.deleteSubject(id);
        return "Subject deleted";
    }
}