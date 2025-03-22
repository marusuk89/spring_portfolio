package com.example.course_registration.service.student;

import com.example.course_registration.common.CoreEntityServiceImpl;
import com.example.course_registration.model.entity.Student;
import com.example.course_registration.repository.student.StudentRepository;

import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentServiceImpl
        extends CoreEntityServiceImpl<Student>
        implements StudentService
{
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        super(studentRepository);
        this.studentRepository = studentRepository;
    }
}
