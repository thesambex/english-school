package com.schoolmanager.english.domain.entities.course;

import com.schoolmanager.english.domain.entities.people.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "teachers", schema = "courses")
@AllArgsConstructor
@Getter
@Setter
public class Teacher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(columnDefinition = "person_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_person_id"))
    private Person person;

    @Column(name = "is_active", nullable = false, columnDefinition = "BOOL DEFAULT true")
    private Boolean isActive;

    @OneToOne(mappedBy = "teacher")
    private CourseClass courseClass;

    public Teacher() {
        this.isActive = true;
    }

}
