package com.schoolmanager.english.infra.repositories;

import com.schoolmanager.english.domain.entities.course.CourseClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JPAClassesRepository extends JpaRepository<CourseClass, UUID> {
    @Override
    @EntityGraph(attributePaths = {"teacher", "course"})
    Page<CourseClass> findAll(Pageable pageable);
}
