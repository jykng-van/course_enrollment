package com.jykng.enrollment.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "teachers")
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    //@JsonManagedReference
    @OneToMany(mappedBy = "teacher", fetch=FetchType.LAZY, cascade = CascadeType.DETACH)
    @JsonIgnoreProperties({"studentCourses","teacher"}) //prevent potential infinitely recursive references
    private List<Course> courses;
}
