package com.schoolmanager.english.infra.services.v1;

import com.schoolmanager.english.application.services.v1.TeachersServiceV1;
import com.schoolmanager.english.domain.dtos.people.PersonDTO;
import com.schoolmanager.english.domain.dtos.standard.ErrorResponseDTO;
import com.schoolmanager.english.domain.entities.course.Teacher;
import com.schoolmanager.english.domain.entities.people.Person;
import com.schoolmanager.english.domain.entities.people.PersonGenres;
import com.schoolmanager.english.infra.repositories.JPAPeopleRepository;
import com.schoolmanager.english.infra.repositories.JPATeachersRepository;
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
public class TeachersServiceV1Impl implements TeachersServiceV1 {

    private final JPAPeopleRepository peopleRepository;
    private final JPATeachersRepository teachersRepository;

    @Transactional
    @Override
    public ResponseEntity<?> createTeacher(PersonDTO body) {
        try {
            if (peopleRepository.findPersonByDocument(body.document()).isPresent()) {
                ErrorResponseDTO response = new ErrorResponseDTO("Already exists an pearson with this document", "document", LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }

            Person person = new Person();
            BeanUtils.copyProperties(body, person);
            person.setId(null);
            person.setGenre(PersonGenres.fromString(body.genre().toUpperCase()));
            person.setBirthdate(LocalDate.parse(body.birthdate()));

            peopleRepository.save(person);

            Teacher teacher = new Teacher();
            teacher.setPerson(person);
            teachersRepository.save(teacher);

            PersonDTO response = new PersonDTO(
                    teacher.getId(),
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
            log.error("Failed to create teacher: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}
