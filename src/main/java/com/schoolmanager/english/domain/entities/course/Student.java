package com.schoolmanager.english.domain.entities.course;

import com.schoolmanager.english.domain.entities.people.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "students", schema = "courses")
@AllArgsConstructor
@Getter
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "coins", columnDefinition = "BIGINT")
    private Long coins;

    @Setter
    @Column(name = "is_active", nullable = false, columnDefinition = "BOOL DEFAULT true")
    private Boolean isActive;

    @Setter
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(columnDefinition = "person_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_person_id"))
    private Person person;

    @OneToMany(mappedBy = "student")
    private Collection<StudentClass> studentClasses;

    public Student() {
        this.isActive = true;
    }

    public void addCoins(long amount) {
        this.coins += amount;
    }

}
