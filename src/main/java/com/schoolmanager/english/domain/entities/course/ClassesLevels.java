package com.schoolmanager.english.domain.entities.course;

import java.util.Arrays;

public enum ClassesLevels {
    ENTRY, MID_LEVEL, ADVANCED;

    public static ClassesLevels fromString(String value) throws IllegalArgumentException {
        return Arrays.stream(values())
                .filter(e -> e.name().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid class level"));
    }

}
