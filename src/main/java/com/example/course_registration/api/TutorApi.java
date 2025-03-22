package com.example.course_registration.api;

import com.example.course_registration.facade.tutor.TutorFacade;
import com.example.course_registration.model.tutor.PersistableTutor;
import com.example.course_registration.model.tutor.ReadableTutor;
import com.example.course_registration.model.tutor.TutorCourseRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/tutors", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TutorApi
{
    private final TutorFacade tutorFacade;

    @Operation(summary = "튜터 생성")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReadableTutor createTutor(@RequestBody @Valid PersistableTutor request) {
        return tutorFacade.createTutor(request);
    }

    @Operation(summary = "튜터 조회")
    @GetMapping("/{id}")
    @Parameters({
        @Parameter(name = "id", in = ParameterIn.PATH, description = "튜터 ID")
    })
    public ReadableTutor getTutor(@PathVariable Long id)
    {
        return tutorFacade.getTutorById(id);
    }

    @Operation(summary = "튜터의 수업 등록(THIRTY/SIXTY)")
    @PostMapping("/{id}/availability")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAvailability(
            @PathVariable Long id,
            @RequestBody @Valid TutorCourseRequest request
    ) {
        tutorFacade.registerAvailability(id, request.getStartTime(), request.getDuration().getMinutes());
    }

    @Operation(summary = "튜터의 특정 시간 수업 삭제")
    @DeleteMapping("/{id}/availability")
    @Parameters({
        @Parameter(name = "id", in = ParameterIn.PATH, description = "튜터 ID")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAvailability(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime availableTime
    ) {
        tutorFacade.removeAvailability(id, availableTime);
    }
}
