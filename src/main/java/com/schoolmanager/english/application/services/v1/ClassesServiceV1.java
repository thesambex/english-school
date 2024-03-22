package com.schoolmanager.english.application.services.v1;

import com.schoolmanager.english.domain.dtos.courses.AddStudentToClassDTO;
import com.schoolmanager.english.domain.dtos.courses.ClassDTO;
import com.schoolmanager.english.domain.dtos.courses.CreateClassDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ClassesServiceV1 {
    ResponseEntity<?> createClass(CreateClassDTO body);
    ResponseEntity<?> addStudent(AddStudentToClassDTO body);
    ResponseEntity<Page<ClassDTO>> findAll(Pageable pageable);
}
