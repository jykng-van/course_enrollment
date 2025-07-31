package com.jykng.enrollment.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "students")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "name")
    String name;

    /* @ManyToMany
    @JoinTable(
        name="student_courses",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses; */

    @OneToMany(mappedBy = "student", fetch=FetchType.EAGER)
    @JsonIgnoreProperties({"student","id"}) //prevent potential infinitely recursive references
    List<StudentCourse> studentCourses;
}