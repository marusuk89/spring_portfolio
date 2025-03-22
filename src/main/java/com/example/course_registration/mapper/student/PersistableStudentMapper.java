package com.example.course_registration.mapper.student;

import com.example.course_registration.model.entity.Student;
import com.example.course_registration.model.student.PersistableStudent;
import org.springframework.stereotype.Component;

@Component
public class PersistableStudentMapper {
    public Student convert(PersistableStudent source) {
        Student student = new Student();
        student.setName(source.getName());
        return student;
    }
}