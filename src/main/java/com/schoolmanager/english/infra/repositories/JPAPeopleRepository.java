package com.schoolmanager.english.infra.repositories;

import com.schoolmanager.english.domain.entities.people.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JPAPeopleRepository extends JpaRepository<Person, UUID> {
    Optional<Person> findPersonByDocument(String document);
}
