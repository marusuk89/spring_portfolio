package com.example.course_registration.model.course;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadableCourse {

    private LocalDateTime startTime;

    private int duration;

    private String tutorName;

    private String studentName;
}
