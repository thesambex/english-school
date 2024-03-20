package com.schoolmanager.english.application.services.v1;

import com.schoolmanager.english.domain.dtos.courses.CourseDTO;
import org.springframework.http.ResponseEntity;

public interface CoursesServiceV1 {
    ResponseEntity<?> createCourse(CourseDTO body);
}
