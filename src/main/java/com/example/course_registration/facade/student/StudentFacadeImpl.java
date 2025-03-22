package com.example.course_registration.facade.student;

import com.example.course_registration.mapper.student.PersistableStudentMapper;
import com.example.course_registration.mapper.student.ReadableStudentMapper;
import com.example.course_registration.model.student.PersistableStudent;
import com.example.course_registration.model.student.ReadableStudent;
import com.example.course_registration.model.entity.Student;
import com.example.course_registration.service.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentFacadeImpl implements StudentFacade {
    private final StudentService studentService;
    private final PersistableStudentMapper persistableStudentMapper;
    private final ReadableStudentMapper readableStudentMapper;

    @Override
    public ReadableStudent createStudent(PersistableStudent student) {
        Student saved = studentService.create(persistableStudentMapper.convert(student));
        return readableStudentMapper.convert(saved);
    }
}
