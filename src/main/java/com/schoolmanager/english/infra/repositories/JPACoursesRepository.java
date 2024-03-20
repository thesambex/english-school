package com.schoolmanager.english.infra.repositories;

import com.schoolmanager.english.domain.entities.course.Course;
import com.schoolmanager.english.domain.entities.course.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JPACoursesRepository extends JpaRepository<Course, UUID> {
    Optional<Course> findCourseByLanguage(Languages language);
}
