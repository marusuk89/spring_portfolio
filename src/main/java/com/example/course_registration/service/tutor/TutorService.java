package com.example.course_registration.service.tutor;

import com.example.course_registration.common.CoreEntityService;
import com.example.course_registration.model.entity.Tutor;

import java.time.LocalDateTime;

public interface TutorService
        extends CoreEntityService<Tutor>
{
    void registerAvailability(Long tutorId, LocalDateTime availableTime, int duration);
    void removeAvailability(Long tutorId, LocalDateTime availableTime);
}
