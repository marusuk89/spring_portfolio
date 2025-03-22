package com.example.course_registration.facade.course;

import com.example.course_registration.model.course.AvailableTutorResponse;
import com.example.course_registration.model.course.CourseDuration;
import com.example.course_registration.model.course.ReadableCourse;
import com.example.course_registration.model.entity.Course;

import java.time.LocalDateTime;
import java.util.List;

public interface CourseFacade
{
    List<Course> findAvailableCourses(LocalDateTime startTime, int duration);
    List<LocalDateTime> findAvailableTimes(LocalDateTime startTime, LocalDateTime endTime, CourseDuration duration);
    List<AvailableTutorResponse> findTutorsForTime(LocalDateTime time);
    ReadableCourse registerCourse(Long studentId, Long tutorId, LocalDateTime startTime, int duration);
    List<ReadableCourse> getCoursesByStudent(Long studentId);
}
