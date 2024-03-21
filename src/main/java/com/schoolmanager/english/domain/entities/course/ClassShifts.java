package com.schoolmanager.english.domain.entities.course;

import java.util.Arrays;

public enum ClassShifts {
    MORNING, AFTERNOON, NOCTURNAL;

    public static ClassShifts fromString(String value) throws IllegalArgumentException {
        return Arrays.stream(values())
                .filter(e -> e.name().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid shift"));
    }

}
