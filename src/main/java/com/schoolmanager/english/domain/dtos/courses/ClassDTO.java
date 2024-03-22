package com.schoolmanager.english.domain.dtos.courses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record ClassDTO(
        UUID id,
        String name,
        String level,
        String shift,
        @JsonProperty("day_of_week")
        String dayOfWeek,
        TeacherDTO teacher,
        CourseDTO course
) {
}
