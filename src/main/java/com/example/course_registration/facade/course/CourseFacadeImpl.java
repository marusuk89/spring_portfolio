package com.example.course_registration.facade.course;

import com.example.course_registration.mapper.course.AvailableTutorResponseMapper;
import com.example.course_registration.mapper.course.ReadableCourseMapper;
import com.example.course_registration.model.course.AvailableTutorResponse;
import com.example.course_registration.model.course.CourseDuration;
import com.example.course_registration.model.course.ReadableCourse;
import com.example.course_registration.model.entity.Course;
import com.example.course_registration.service.course.CourseService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseFacadeImpl implements CourseFacade
{
    private final CourseService courseService;

    @Override
    public List<Course> findAvailableCourses(LocalDateTime startTime, int duration) {
        return courseService.findAvailableCourses(startTime, duration);
    }

    @Override
    public List<LocalDateTime> findAvailableTimes(LocalDateTime startTime, LocalDateTime endTime, CourseDuration duration) {
        return courseService.findAvailableTimes(startTime, endTime, duration);
    }

    @Override
    public List<AvailableTutorResponse> findTutorsForTime(LocalDateTime time) {
        List<Course> courses = courseService.findCoursesForTime(time);

        return courses.stream()
                .filter(course -> course.getStudent() == null)
                .map(course -> AvailableTutorResponseMapper.convert(
                        course.getTutor(),
                        course.getStartTime(),
                        course.getDuration()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ReadableCourse registerCourse(Long studentId, Long tutorId, LocalDateTime startTime, int duration) {
        Course course = courseService.registerCourse(studentId, tutorId, startTime, duration);
        return ReadableCourseMapper.convert(course);
    }

    @Override
    public List<ReadableCourse> getCoursesByStudent(Long studentId) {
        List<Course> courses = courseService.getCoursesByStudent(studentId);
        return courses.stream()
                .map(ReadableCourseMapper::convert)
                .collect(Collectors.toList());
    }
}
