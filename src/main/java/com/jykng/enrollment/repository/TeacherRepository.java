package com.jykng.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jykng.enrollment.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{
    //custom methods go here
}
