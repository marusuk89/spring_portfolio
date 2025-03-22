package com.example.course_registration.api;

import com.example.course_registration.facade.course.CourseFacade;
import com.example.course_registration.model.course.AvailableTutorResponse;
import com.example.course_registration.model.course.CourseDuration;
import com.example.course_registration.model.course.ReadableCourse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/courses", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CourseApi
{
    private final CourseFacade courseFacade;

    @Operation(summary = "특정 기간 & 수업 길이로 가능한 수업 시간대 조회")
    @GetMapping("/available")
    public List<LocalDateTime> getAvailableTimes(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
            @RequestParam CourseDuration duration
    ) {
        return courseFacade.findAvailableTimes(startTime, endTime, duration);
    }

    @Operation(summary = "특정 시간대에 수업 가능 튜터 조회")
    @GetMapping("/available-tutors")
    public List<AvailableTutorResponse> getAvailableTutors(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time
    ) {
        return courseFacade.findTutorsForTime(time);
    }

    @Operation(summary = "학생이 특정 시간에 수업 신청")
    @PostMapping("/{studentId}/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Parameters({
        @Parameter(name = "studentId", in = ParameterIn.PATH, description = "학생 ID"),
        @Parameter(name = "startTime", description = "수업 시작 시간"),
        @Parameter(name = "duration", description = "수업 길이 (30 또는 60 분)"),
        @Parameter(name = "tutorId", description = "튜터 ID")
    })
    public ReadableCourse registerCourse(
            @PathVariable Long studentId,
            @RequestParam LocalDateTime startTime,
            @RequestParam int duration,
            @RequestParam Long tutorId
    ) {
        return courseFacade.registerCourse(studentId, tutorId, startTime, duration);
    }

    @Operation(summary = "학생이 신청한 수업 조회")
    @GetMapping("/{studentId}")
    @Parameters(value = {
            @Parameter(name = "studentId", in = ParameterIn.PATH, description = "학생 ID")
    })
    public List<ReadableCourse> getStudentCourses(@PathVariable Long studentId)
    {
        return courseFacade.getCoursesByStudent(studentId);
    }
}
