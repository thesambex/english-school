package com.schoolmanager.english.controller.v1;

import com.schoolmanager.english.application.services.v1.ClassesServiceV1;
import com.schoolmanager.english.domain.dtos.courses.ClassDTO;
import com.schoolmanager.english.domain.dtos.courses.CreateClassDTO;
import com.schoolmanager.english.domain.dtos.courses.AddStudentToClassDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/classes")
@AllArgsConstructor
public class ClassesControllerV1 {

    private final ClassesServiceV1 classesService;

    @PostMapping("/create")
    public ResponseEntity<?> createClass(@Valid @RequestBody CreateClassDTO body) {
        return classesService.createClass(body);
    }

    @PostMapping("/students/add")
    public ResponseEntity<?> addStudent(@Valid @RequestBody AddStudentToClassDTO body) {
        return classesService.addStudent(body);
    }

    @GetMapping
    public ResponseEntity<Page<ClassDTO>> findAll(Pageable pageable) {
        return classesService.findAll(pageable);
    }

}
