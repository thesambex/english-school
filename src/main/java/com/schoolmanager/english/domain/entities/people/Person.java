package com.schoolmanager.english.domain.entities.people;

import com.schoolmanager.english.domain.entities.accounts.Account;
import com.schoolmanager.english.domain.entities.course.Student;
import com.schoolmanager.english.domain.entities.course.Teacher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "people", schema = "people")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(name = "document", nullable = false, length = 11, unique = true)
    private String document;

    @Column(name = "birthdate", nullable = false, columnDefinition = "DATE")
    private LocalDate birthdate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "genre", nullable = false)
    private PersonGenres genre;

    @OneToOne(mappedBy = "person")
    private Account account;

    @OneToOne(mappedBy = "person")
    private PersonContactInfo contactInfo;

    @OneToOne(mappedBy = "person")
    private Teacher teacher;

    @OneToOne(mappedBy = "person")
    private Student student;

}
