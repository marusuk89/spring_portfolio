package com.example.course_registration.service.course;

import com.example.course_registration.common.CoreEntityService;
import com.example.course_registration.model.course.CourseDuration;
import com.example.course_registration.model.entity.Course;

import java.time.LocalDateTime;
import java.util.List;

public interface CourseService
        extends CoreEntityService<Course> 
{
    List<Course> findAvailableCourses(LocalDateTime startTime, int duration);
    List<LocalDateTime> findAvailableTimes(LocalDateTime startTime, LocalDateTime endTime, CourseDuration duration);
    List<Course> findCoursesForTime(LocalDateTime time);
    Course registerCourse(Long studentId, Long tutorId, LocalDateTime startTime, int duration);
    List<Course> getCoursesByStudent(Long studentId);
}
