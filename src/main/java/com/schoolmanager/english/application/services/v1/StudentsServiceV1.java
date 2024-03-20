package com.schoolmanager.english.application.services.v1;

import com.schoolmanager.english.domain.dtos.people.PersonDTO;
import org.springframework.http.ResponseEntity;

public interface StudentsServiceV1 {
    ResponseEntity<?> createStudent(PersonDTO body);
}
