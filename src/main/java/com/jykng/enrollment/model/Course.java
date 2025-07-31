package com.jykng.enrollment.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "courses")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="end_date")
    private Date endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="subject_id")
    @JsonIgnoreProperties({"courses"}) //prevent potential infinitely recursive references
    private Subject subject;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name="teacher_id")
    @JsonIgnoreProperties({"courses"}) //prevent potential infinitely recursive references
    private Teacher teacher;

    @OneToMany(mappedBy = "course", fetch=FetchType.EAGER)
    @JsonIgnoreProperties({"id","course"}) //prevent potential infinitely recursive references
    private List<StudentCourse> studentCourses; //a many-to-many relationship where the join table has a property
}
