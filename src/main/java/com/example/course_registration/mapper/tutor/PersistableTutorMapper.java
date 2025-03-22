package com.example.course_registration.mapper.tutor;

import com.example.course_registration.model.entity.Tutor;
import com.example.course_registration.model.tutor.PersistableTutor;
import org.springframework.stereotype.Component;

@Component
public class PersistableTutorMapper {

    public Tutor convert(PersistableTutor source) {
        Tutor tutor = new Tutor();
        tutor.setName(source.getName());
        return tutor;
    }
}