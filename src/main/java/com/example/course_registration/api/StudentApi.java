package com.example.course_registration.api;

import com.example.course_registration.facade.student.StudentFacade;
import com.example.course_registration.model.student.PersistableStudent;
import com.example.course_registration.model.student.ReadableStudent;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/students", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class StudentApi {

    private final StudentFacade studentFacade;

    @Operation(summary = "학생 생성")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReadableStudent createStudent(@RequestBody @Valid PersistableStudent student) {
        return studentFacade.createStudent(student);
    }
}
