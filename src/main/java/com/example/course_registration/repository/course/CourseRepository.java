package com.example.course_registration.repository.course;

import com.example.course_registration.model.entity.Course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CourseRepository 
        extends JpaRepository<Course, Long>
{    
        List<Course> findByStartTime(LocalDateTime startTime);
        List<Course> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
        List<Course> findByStudentId(Long studentId);
        List<Course> findByTutorId(Long tutorId);
}
