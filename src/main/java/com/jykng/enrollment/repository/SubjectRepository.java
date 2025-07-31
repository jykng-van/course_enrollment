package com.jykng.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jykng.enrollment.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{
    //custom methods go here

    @Modifying
    @Query("DELETE FROM StudentCourse sc WHERE sc.course.id IN (SELECT c.id FROM Course c WHERE c.subject.id = :id)")
    public void deleteStudentCourses(@Param("id") long id);
}
