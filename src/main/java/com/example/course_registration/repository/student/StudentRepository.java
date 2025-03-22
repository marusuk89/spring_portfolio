package com.example.course_registration.repository.student;

import com.example.course_registration.model.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository
        extends JpaRepository<Student, Long>
{
}