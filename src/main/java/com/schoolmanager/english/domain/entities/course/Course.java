package com.schoolmanager.english.domain.entities.course;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "courses", schema = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "language", nullable = false)
    private Languages language;

    @OneToOne(mappedBy = "course")
    private CourseClass courseClass;

}
