package com.example.course_registration.facade.tutor;

import com.example.course_registration.model.tutor.PersistableTutor;
import com.example.course_registration.model.tutor.ReadableTutor;

import java.time.LocalDateTime;

public interface TutorFacade
{
    ReadableTutor createTutor(PersistableTutor request);
    ReadableTutor getTutorById(Long id);
    void registerAvailability(Long tutorId, LocalDateTime availableTime, int duration);
    void removeAvailability(Long tutorId, LocalDateTime availableTime);
}
