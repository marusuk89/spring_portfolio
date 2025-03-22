package com.example.course_registration.mapper.tutor;

import com.example.course_registration.model.entity.Tutor;
import com.example.course_registration.model.tutor.ReadableTutor;
import org.springframework.stereotype.Component;

@Component
public class ReadableTutorMapper {
    public ReadableTutor convert(Tutor source) {
        ReadableTutor target = new ReadableTutor();
        target.setId(source.getId());
        target.setName(source.getName());
        return target;
    }
}
