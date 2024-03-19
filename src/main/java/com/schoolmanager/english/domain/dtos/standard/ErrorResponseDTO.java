package com.schoolmanager.english.domain.dtos.standard;

import java.time.LocalDateTime;

public record ErrorResponseDTO(String message, String data, LocalDateTime time) {
}
