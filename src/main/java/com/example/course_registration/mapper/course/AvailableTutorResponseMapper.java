package com.example.course_registration.mapper.course;

import com.example.course_registration.model.course.AvailableTutorResponse;
import com.example.course_registration.model.entity.Tutor;

import java.time.LocalDateTime;

public class AvailableTutorResponseMapper {

    public static AvailableTutorResponse convert(Tutor tutor, LocalDateTime startTime, int duration) {
        AvailableTutorResponse reponse = new AvailableTutorResponse();
        reponse.setTutorName(tutor.getName());
        reponse.setStartTime(startTime);
        reponse.setDuration(duration);
        return reponse;
    }
}
