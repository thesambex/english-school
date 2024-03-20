package com.schoolmanager.english.controller.v1;

import com.schoolmanager.english.application.services.v1.TeachersServiceV1;
import com.schoolmanager.english.domain.dtos.people.PersonDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teachers")
@AllArgsConstructor
public class TeachersControllerV1 {

    private final TeachersServiceV1 teachersService;

    @PostMapping("/create")
    public ResponseEntity<?> createTeacher(@Valid @RequestBody PersonDTO body) {
        return teachersService.createTeacher(body);
    }

}
