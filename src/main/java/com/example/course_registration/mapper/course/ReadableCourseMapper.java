package com.example.course_registration.mapper.course;

import com.example.course_registration.model.course.ReadableCourse;
import com.example.course_registration.model.entity.Course;
import com.example.course_registration.model.entity.Student;
import com.example.course_registration.model.entity.Tutor;

public class ReadableCourseMapper {

    public static ReadableCourse convert(Course course) {
        ReadableCourse response = new ReadableCourse();
        response.setStartTime(course.getStartTime());
        response.setDuration(course.getDuration());

        Tutor tutor = course.getTutor();
        if (tutor != null) {
            response.setTutorName(tutor.getName());
        }

        Student student = course.getStudent();
        if (student != null) {
            response.setStudentName(student.getName());
        }

        return response;
    }
}
