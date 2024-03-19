package com.schoolmanager.english.domain.entities.people;

import java.util.Arrays;

public enum PersonGenres {
    UNKNOWN, MALE, FEMALE;

    public static PersonGenres fromString(String value) {
        return Arrays.stream(values())
                .filter(e -> e.name().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid person genre"));
    }

}
