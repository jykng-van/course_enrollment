package com.jykng.enrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jykng.enrollment.model.Student;
import com.jykng.enrollment.model.StudentCourse;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // custom query methods can
    @Query("SELECT sc FROM StudentCourse sc WHERE sc.student.id=:id")
    public List<StudentCourse> getStudentCourses(@Param("id") long id);

    @Modifying
    @Query("DELETE StudentCourse sc WHERE sc.student.id=:id")
    public void deleteStudentCourses(@Param("id") long id);

    @Modifying
    @Query(value="INSERT INTO student_courses (student_id, course_id) VALUES (:student_id, :course_id)", nativeQuery=true)
    public void enrollCourse(@Param("student_id") long student_id, @Param("course_id") long course_id);
}