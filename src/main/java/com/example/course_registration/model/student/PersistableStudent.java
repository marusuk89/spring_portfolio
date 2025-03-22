package com.example.course_registration.model.student;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter @Setter
public class PersistableStudent {
    @NotBlank
    private String name;
}