package com.schoolmanager.english.controller.v1;

import com.schoolmanager.english.application.services.v1.ClassesServiceV1;
import com.schoolmanager.english.domain.dtos.courses.CreateClassDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/classes")
@AllArgsConstructor
public class ClassesControllerV1 {

    private final ClassesServiceV1 classesService;

    @PostMapping("/create")
    public ResponseEntity<?> createClass(@Valid @RequestBody CreateClassDTO body) {
        return classesService.createClass(body);
    }

}
