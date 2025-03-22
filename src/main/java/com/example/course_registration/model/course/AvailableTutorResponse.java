package com.example.course_registration.model.course;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "수업 가능 튜터 응답")
public class AvailableTutorResponse {

    @Schema(description = "튜터 이름")
    private String tutorName;

    @Schema(description = "수업 시작 시간")
    private LocalDateTime startTime;

    @Schema(description = "수업 길이 (분 단위)")
    private int duration;
}
