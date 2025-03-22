package com.example.course_registration.model.tutor;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter @Setter
public class PersistableTutor
{
    @NotBlank
    private String name;
}
