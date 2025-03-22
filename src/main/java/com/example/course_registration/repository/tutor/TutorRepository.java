package com.example.course_registration.repository.tutor;

import com.example.course_registration.model.entity.Tutor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository
    extends JpaRepository<Tutor, Long>
{
}
