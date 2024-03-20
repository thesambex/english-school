package com.schoolmanager.english.infra.services.v1;

import com.schoolmanager.english.application.services.v1.StudentsServiceV1;
import com.schoolmanager.english.domain.dtos.people.PersonDTO;
import com.schoolmanager.english.domain.dtos.standard.ErrorResponseDTO;
import com.schoolmanager.english.domain.entities.course.Student;
import com.schoolmanager.english.domain.entities.people.Person;
import com.schoolmanager.english.domain.entities.people.PersonGenres;
import com.schoolmanager.english.infra.repositories.JPAPeopleRepository;
import com.schoolmanager.english.infra.repositories.JPAStudentsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Log4j2
public class StudentsServiceV1Impl implements StudentsServiceV1 {

    private final JPAPeopleRepository peopleRepository;
    private final JPAStudentsRepository studentsRepository;

    @Transactional
    @Override
    public ResponseEntity<?> createStudent(PersonDTO body) {
        try {
            if (peopleRepository.findPersonByDocument(body.document()).isPresent()) {
                ErrorResponseDTO response = new ErrorResponseDTO("Already exists an student with this document", "document", LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }

            Person person = new Person();
            BeanUtils.copyProperties(body, person);
            person.setBirthdate(LocalDate.parse(body.birthdate()));
            person.setGenre(PersonGenres.fromString(body.genre().toUpperCase()));
            peopleRepository.save(person);

            Student student = new Student();
            student.setPerson(person);
            studentsRepository.save(student);

            PersonDTO response = new PersonDTO(
                    person.getId(),
                    person.getFirstName(),
                    person.getLastName(),
                    person.getDocument(),
                    person.getBirthdate().toString(),
                    person.getGenre().toString()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ErrorResponseDTO response = new ErrorResponseDTO("Bad request", e.getMessage(), LocalDateTime.now());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            log.error("Failed to create student: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}
