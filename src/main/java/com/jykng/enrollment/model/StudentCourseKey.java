package com.jykng.enrollment.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

//Composite Key because student_course requires one
@Embeddable
class StudentCourseKey implements Serializable {

    @Column(name = "student_id")
    Long studentId;

    @Column(name = "course_id")
    Long courseId;

    // Constructors
    public StudentCourseKey() {}

    public StudentCourseKey(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }


    // hashcode and equals implementation
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCourseKey that = (StudentCourseKey) o;
        //Check if the student_id and course_id are the same
        return Objects.equals(studentId, that.studentId) &&
               Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() { //make a hashcode
        return Objects.hash(studentId, courseId);
    }
}