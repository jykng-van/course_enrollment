package com.jykng.enrollment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "student_courses")
@Data
public class StudentCourse {
    @EmbeddedId
    private StudentCourseKey id; //primary key of student_courses is both student_id and course_id

    @Column(name="grade")
    private Float grade;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @MapsId("studentId")
    @JoinColumn(name="student_id")
    @JsonIgnoreProperties({"studentCourses"}) //prevent potential infinitely recursive references
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @MapsId("courseId")
    @JoinColumn(name="course_id")
    @JsonIgnoreProperties({"studentCourses"}) //prevent potential infinitely recursive references
    private Course course;

}
