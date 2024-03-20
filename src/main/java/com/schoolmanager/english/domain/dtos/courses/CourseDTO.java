package com.schoolmanager.english.domain.dtos.courses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CourseDTO(
        UUID id,
        @NotBlank(message = "provide course name")
        @Size(max = 40, message = "name max length is 40 characters")
        String name,
        @NotBlank(message = "provide course language")
        @Size(max = 7, message = "language max length is 7 characters")
        String language
) {
}
