package com.example.course_registration.model.course;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "수업 가능한 시간대 조회 요청")
public class AvailableTimeRequest {

    @Schema(description = "조회 시작 날짜", example = "2025-03-22")
    @NotNull
    private LocalDate startDate;

    @Schema(description = "조회 종료 날짜", example = "2025-03-25")
    @NotNull
    private LocalDate endDate;

    @Schema(description = "수업 길이", allowableValues = {"THIRTY", "SIXTY"}, example = "THIRTY")
    @NotNull
    private CourseDuration duration;
}
