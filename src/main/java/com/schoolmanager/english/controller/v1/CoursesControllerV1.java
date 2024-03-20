package com.schoolmanager.english.controller.v1;

import com.schoolmanager.english.application.services.v1.CoursesServiceV1;
import com.schoolmanager.english.domain.dtos.courses.CourseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses")
@AllArgsConstructor
public class CoursesControllerV1 {

    private final CoursesServiceV1 coursesService;

    @PostMapping("/create")
    private ResponseEntity<?> createCourse(@Valid @RequestBody CourseDTO body) {
        return coursesService.createCourse(body);
    }

}
