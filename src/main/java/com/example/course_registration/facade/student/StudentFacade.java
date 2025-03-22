package com.example.course_registration.facade.student;

import com.example.course_registration.model.student.PersistableStudent;
import com.example.course_registration.model.student.ReadableStudent;

public interface StudentFacade {
    ReadableStudent createStudent(PersistableStudent student);
}