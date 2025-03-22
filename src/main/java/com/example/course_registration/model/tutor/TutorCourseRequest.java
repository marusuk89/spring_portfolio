package com.example.course_registration.model.tutor;

import io.swagger.v3.oas.annotations.media.Schema;

import com.example.course_registration.model.course.CourseDuration;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(name = "TutorCourseRequest", description = "튜터 수업 요청")
@Getter @Setter
public class TutorCourseRequest {

    @Schema(description = "수업 시작 시간", example = "2025-03-22T12:00:00")
    private LocalDateTime startTime;

    @Schema(description = "수업 길이 (30분 또는 60분)", allowableValues = {"THIRTY", "SIXTY"})
    private CourseDuration duration;
}