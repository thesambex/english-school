package com.schoolmanager.english.infra.repositories;

import com.schoolmanager.english.domain.entities.course.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JPATeachersRepository extends JpaRepository<Teacher, UUID> {

}
