package com.schoolmanager.english.domain.entities.course;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "students_classes", schema = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentClass implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "student_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_student_id"), insertable = false, updatable = false)
    private Student student;

    @Column(name = "student_id")
    private UUID studentId;

    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "class_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_class_id"), insertable = false, updatable = false)
    private CourseClass courseClass;

    @Column(name = "class_id")
    private UUID classId;

}
