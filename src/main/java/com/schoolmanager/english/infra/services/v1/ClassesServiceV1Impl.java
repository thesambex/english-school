package com.schoolmanager.english.infra.services.v1;

import com.schoolmanager.english.application.services.v1.ClassesServiceV1;
import com.schoolmanager.english.domain.dtos.courses.*;
import com.schoolmanager.english.domain.dtos.standard.ErrorResponseDTO;
import com.schoolmanager.english.domain.entities.course.*;
import com.schoolmanager.english.infra.repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class ClassesServiceV1Impl implements ClassesServiceV1 {

    private final JPAClassesRepository classesRepository;
    private final JPAStudentsClassesRepository studentsClassesRepository;
    private final JPACoursesRepository coursesRepository;
    private final JPATeachersRepository teachersRepository;
    private final JPAStudentsRepository studentsRepository;

    @Override
    public ResponseEntity<?> createClass(CreateClassDTO body) {
        try {
            if(teachersRepository.findById(body.teacherId()).isEmpty()) {
                ErrorResponseDTO response = new ErrorResponseDTO("This teacher does not exist", "teacher", LocalDateTime.now());
                return ResponseEntity.badRequest().body(response);
            }

            if(coursesRepository.findById(body.courseId()).isEmpty()) {
                ErrorResponseDTO response = new ErrorResponseDTO("This course does not exist", "course", LocalDateTime.now());
                return ResponseEntity.badRequest().body(response);
            }

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

    @Override
    public ResponseEntity<?> addStudent(AddStudentToClassDTO body) {
        try {
            if(studentsRepository.findById(body.studentId()).isEmpty()) {
                ErrorResponseDTO response = new ErrorResponseDTO("This student does not exist", "student", LocalDateTime.now());
                return ResponseEntity.badRequest().body(response);
            }

            if(classesRepository.findById(body.classId()).isEmpty()) {
                ErrorResponseDTO response = new ErrorResponseDTO("This class does not exist", "class", LocalDateTime.now());
                return ResponseEntity.badRequest().body(response);
            }

            if (studentsClassesRepository.findDuplicatedStudent(body.classId(), body.studentId()).isPresent()) {
                ErrorResponseDTO response = new ErrorResponseDTO("This student already in this class", null, LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }

            StudentClass studentClass = new StudentClass();
            studentClass.setClassId(body.classId());
            studentClass.setStudentId(body.studentId());

            studentsClassesRepository.save(studentClass);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.error("Failed to add student to class: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Page<ClassDTO>> findAll(Pageable pageable) {
        try {
            Page<CourseClass> classes = classesRepository.findAll(pageable);
            List<ClassDTO> classesDto = classes.stream().map(courseClass -> {
                Teacher teacher = courseClass.getTeacher();
                Course course = courseClass.getCourse();
                return new ClassDTO(
                        courseClass.getId(),
                        courseClass.getName(),
                        courseClass.getLevel().toString(),
                        courseClass.getShift().toString(),
                        courseClass.getDayOfWeek().toString(),
                        new TeacherDTO(teacher.getId(), teacher.getPerson().getFirstName()),
                        new CourseDTO(course.getId(), course.getName(), course.getLanguage().toString())
                );
            }).toList();

            return ResponseEntity.ok(new PageImpl<>(classesDto, pageable, classesDto.size()));
        } catch (Exception e) {
            log.error("Failed to find all classes: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
