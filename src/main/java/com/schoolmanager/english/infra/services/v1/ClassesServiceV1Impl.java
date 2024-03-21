package com.schoolmanager.english.infra.services.v1;

import com.schoolmanager.english.application.services.v1.ClassesServiceV1;
import com.schoolmanager.english.domain.dtos.courses.CreateClassDTO;
import com.schoolmanager.english.domain.dtos.standard.ErrorResponseDTO;
import com.schoolmanager.english.domain.entities.course.ClassShifts;
import com.schoolmanager.english.domain.entities.course.ClassesLevels;
import com.schoolmanager.english.domain.entities.course.CourseClass;
import com.schoolmanager.english.infra.repositories.JPAClassesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Log4j2
public class ClassesServiceV1Impl implements ClassesServiceV1 {

    private final JPAClassesRepository classesRepository;

    @Override
    public ResponseEntity<?> createClass(CreateClassDTO body) {
        try {
            CourseClass courseClass = new CourseClass();
            courseClass.setName(body.name());
            courseClass.setLevel(ClassesLevels.fromString(body.level().toUpperCase()));
            courseClass.setShift(ClassShifts.fromString(body.shift().toUpperCase()));
            courseClass.setDayOfWeek(DayOfWeek.valueOf(body.dayOfWeek()));
            courseClass.setTeacherId(body.teacherId());
            courseClass.setCourseId(body.courseId());

            classesRepository.save(courseClass);

            CreateClassDTO response = new CreateClassDTO(
                    courseClass.getId(),
                    courseClass.getName(),
                    courseClass.getLevel().toString(),
                    courseClass.getShift().toString(),
                    courseClass.getDayOfWeek().toString(),
                    courseClass.getTeacherId(),
                    courseClass.getCourseId()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ErrorResponseDTO response = new ErrorResponseDTO("Bad request", e.getMessage(), LocalDateTime.now());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            log.error("Failed to create a class: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}
