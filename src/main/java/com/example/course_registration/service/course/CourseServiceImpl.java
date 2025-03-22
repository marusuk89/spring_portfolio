package com.example.course_registration.service.course;

import com.example.course_registration.common.CoreEntityServiceImpl;
import com.example.course_registration.model.course.CourseDuration;
import com.example.course_registration.model.entity.Course;
import com.example.course_registration.model.entity.Student;
import com.example.course_registration.repository.course.CourseRepository;
import com.example.course_registration.repository.student.StudentRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("courseService")
public class CourseServiceImpl
        extends CoreEntityServiceImpl<Course>
        implements CourseService
{
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        super(courseRepository);
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Course> findAvailableCourses(LocalDateTime startTime, int duration) {
        return courseRepository.findByStartTime(startTime).stream()
                .filter(course -> course.getStudent() == null && course.getDuration() == duration)
                .collect(Collectors.toList());
    }

    @Override
    public List<LocalDateTime> findAvailableTimes(LocalDateTime startTime, LocalDateTime endTime, CourseDuration duration) {
        List<Course> courses = courseRepository.findByStartTimeBetween(startTime, endTime);

        Set<LocalDateTime> availableTimes = new HashSet<>();

        for (Course course : courses) {
            if (course.getStudent() == null && course.getDuration() == duration.getMinutes()) {
                availableTimes.add(course.getStartTime());
            }
        }

        return availableTimes.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> findCoursesForTime(LocalDateTime time) {
        return courseRepository.findByStartTime(time);
    }

    @Override
    public Course registerCourse(Long studentId, Long tutorId, LocalDateTime startTime, int duration) {
        Course course = courseRepository.findByStartTime(startTime).stream()
                .filter(c -> c.getTutor().getId().equals(tutorId))
                .filter(c -> c.getDuration() == duration)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 조건의 수업이 존재하지 않습니다."));

        if (course.getStudent() != null) {
            throw new IllegalStateException("이미 수강 신청된 수업입니다.");
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 학생이 존재하지 않습니다."));
        course.setStudent(student);

        return courseRepository.save(course);
    }

    @Override
    public List<Course> getCoursesByStudent(Long studentId) {
        return courseRepository.findByStudentId(studentId);
    }
}