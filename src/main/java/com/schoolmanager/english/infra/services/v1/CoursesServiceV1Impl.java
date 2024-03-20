package com.schoolmanager.english.infra.services.v1;

import com.schoolmanager.english.application.services.v1.CoursesServiceV1;
import com.schoolmanager.english.domain.dtos.courses.CourseDTO;
import com.schoolmanager.english.domain.dtos.standard.ErrorResponseDTO;
import com.schoolmanager.english.domain.entities.course.Course;
import com.schoolmanager.english.domain.entities.course.Languages;
import com.schoolmanager.english.infra.repositories.JPACoursesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Log4j2
public class CoursesServiceV1Impl implements CoursesServiceV1 {

    private final JPACoursesRepository coursesRepository;

    @Override
    public ResponseEntity<?> createCourse(CourseDTO body) {
        try {
            Course course = new Course();
            course.setName(body.name());
            course.setLanguage(Languages.fromString(body.language().toUpperCase()));

            coursesRepository.save(course);

            CourseDTO response = new CourseDTO(course.getId(), course.getName(), course.getLanguage().toString());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ErrorResponseDTO response = new ErrorResponseDTO("Bad request", e.getMessage(), LocalDateTime.now());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            log.error("Failed to create course: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}
