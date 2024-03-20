package com.schoolmanager.english.controller.v1;

import com.schoolmanager.english.application.services.v1.StudentsServiceV1;
import com.schoolmanager.english.domain.dtos.people.PersonDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentsControllerV1 {

    private final StudentsServiceV1 studentsService;

    @PostMapping("/create")
    public ResponseEntity<?> createStudent(@Valid @RequestBody PersonDTO body) {
        return studentsService.createStudent(body);
    }

}
