package com.schoolmanager.english.domain.dtos.courses;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreateClassDTO(
        UUID id,
        @NotBlank(message = "provide class name")
        @Size(max = 40, message = "class name max length is 40 characters")
        String name,
        @NotBlank(message = "provide class level")
        @Size(max = 9, message = "level max length is 9 characters")
        String level,
        @NotBlank(message = "provide class shift")
        @Size(max = 10, message = "shift max length is 10 characters")
        String shift,
        @NotBlank(message = "provide day_of_week")
        @Size(max = 10, message = "day_of_week max length is 10 characters")
        @JsonProperty("day_of_week")
        String dayOfWeek,
        @NotNull(message = "provide teacher_id")
        @JsonProperty("teacher_id")
        UUID teacherId,
        @NotNull(message = "provide course_id")
        @JsonProperty("course_id")
        UUID courseId
) {

}
