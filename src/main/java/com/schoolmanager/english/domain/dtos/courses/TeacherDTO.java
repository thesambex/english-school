package com.schoolmanager.english.domain.dtos.courses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record TeacherDTO(UUID id, @JsonProperty("first_name") String firstName) {
}
