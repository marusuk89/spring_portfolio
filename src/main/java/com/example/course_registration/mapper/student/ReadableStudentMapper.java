package com.example.course_registration.mapper.student;

import com.example.course_registration.model.entity.Student;
import com.example.course_registration.model.student.ReadableStudent;
import org.springframework.stereotype.Component;

@Component
public class ReadableStudentMapper {
    public ReadableStudent convert(Student source) {
        ReadableStudent target = new ReadableStudent();
        target.setId(source.getId());
        target.setName(source.getName());
        return target;
    }
}
