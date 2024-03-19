package com.schoolmanager.english.infra.repositories;

import com.schoolmanager.english.domain.entities.course.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JPAStudentsRepository extends JpaRepository<Student, UUID> {
}
