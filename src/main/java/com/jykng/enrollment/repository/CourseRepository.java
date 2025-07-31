package com.jykng.enrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jykng.enrollment.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
    //custom methods go here
    @Query("SELECT c FROM Course c WHERE c.teacher.id = :id")
    public List<Course> findByTeacherId(@Param("id") long id);

    @Query("UPDATE Course c SET c.teacher=NULL WHERE c.teacher.id = :id")
    @Modifying
    public void removeTeacherById(@Param("id") long id);

    @Query("DELETE Course c WHERE c.subject.id = :id")
    @Modifying
    public void deleteBySubjectId(@Param("id") long id);

    @Query("DELETE StudentCourse sc WHERE sc.course.id=:id")
    @Modifying
    public void deleteStudentCourses(@Param("id") long id);

    @Query("DELETE Course c WHERE c.id = :id")
    @Modifying
    public void reallyDeleteById(@Param("id") long id);

    @Query("SELECT c FROM Course c WHERE LOWER(c.subject.name) LIKE %:search%")
    public List<Course> findAllWithSearch(@Param("search") String search);

    //complications because teacher_id can be null
    @Query(value="UPDATE courses SET subject_id=:subject_id, teacher_id=CASE WHEN :teacher_id!=0 THEN :teacher_id ELSE NULL END, start_date=:start_date, end_date=:end_date WHERE id=:id", nativeQuery=true)
    @Modifying
    public void updateCourse(@Param("subject_id") long subject_id, @Param("teacher_id") Long teacher_id, @Param("start_date") String start_date,
    @Param("end_date") String end_date, @Param("id") long id);

    //find courses in the future, 1 week grace period for late enrollment
    @Query(value="SELECT c.* FROM courses c "
    +"WHERE DATEADD('WEEK', 1, c.start_date) > NOW() AND "
    +"c.id NOT IN (SELECT course_id FROM student_courses WHERE student_id=:student_id) ", nativeQuery = true)
    public List<Course> getFutureCourses(@Param("student_id") long student_id);
}
