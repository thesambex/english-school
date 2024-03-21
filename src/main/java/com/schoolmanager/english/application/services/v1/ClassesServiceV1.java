package com.schoolmanager.english.application.services.v1;

import com.schoolmanager.english.domain.dtos.courses.CreateClassDTO;
import org.springframework.http.ResponseEntity;

public interface ClassesServiceV1 {
    ResponseEntity<?> createClass(CreateClassDTO body);
}
