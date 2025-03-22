package com.example.course_registration.facade.tutor;

import com.example.course_registration.model.entity.Tutor;
import com.example.course_registration.model.tutor.PersistableTutor;
import com.example.course_registration.model.tutor.ReadableTutor;
import com.example.course_registration.service.tutor.TutorService;
import com.example.course_registration.mapper.tutor.PersistableTutorMapper;
import com.example.course_registration.mapper.tutor.ReadableTutorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TutorFacadeImpl
        implements TutorFacade
{
    private final TutorService tutorService;

    private final PersistableTutorMapper persistableTutorMapper;

    private final ReadableTutorMapper readableTutorMapper;

    @Override
    public ReadableTutor createTutor(PersistableTutor request) {
        Tutor tutor = persistableTutorMapper.convert(request);
        Tutor saved = tutorService.create(tutor);
        return readableTutorMapper.convert(saved);
    }

    @Override
    public ReadableTutor getTutorById(Long id) {
        Tutor tutor = tutorService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 튜터가 존재하지 않습니다: " + id));
        return readableTutorMapper.convert(tutor);
    }
    
    @Override
    public void registerAvailability(Long tutorId, LocalDateTime availableTime, int duration) {
        tutorService.registerAvailability(tutorId, availableTime, duration);
    }
    
    @Override
    public void removeAvailability(Long tutorId, LocalDateTime availableTime) {
        tutorService.removeAvailability(tutorId, availableTime);
    }
}

