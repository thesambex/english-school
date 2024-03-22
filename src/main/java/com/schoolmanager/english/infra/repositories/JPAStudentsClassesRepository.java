package com.schoolmanager.english.infra.repositories;

import com.schoolmanager.english.domain.entities.course.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public interface JPAStudentsClassesRepository extends JpaRepository<StudentClass, UUID> {

    default Optional<StudentClass> findDuplicatedStudent(UUID classId, UUID studentId) {
        return findAll()
                .stream()
                .filter(e -> e.getClassId().equals(classId) && e.getStudentId().equals(studentId))
                .findFirst();
    }

    default Collection<StudentClass> findStudentsByClassId(UUID classId) {
        return findAll()
                .stream()
                .filter(e -> e.getClassId().equals(classId))
                .collect(Collectors.toList());
    }

}
