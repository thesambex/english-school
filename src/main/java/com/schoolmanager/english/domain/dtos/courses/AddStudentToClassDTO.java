package com.schoolmanager.english.domain.dtos.courses;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddStudentToClassDTO(
        @NotNull
        @JsonProperty("class_id")
        UUID classId,
        @NotNull
        @JsonProperty("student_id")
        UUID studentId
) {
}
