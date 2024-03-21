package com.schoolmanager.english.domain.entities.course;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "classes", schema = "courses")
@AllArgsConstructor
@Getter
@Setter
public class CourseClass implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "level", nullable = false)
    private ClassesLevels level;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "shift", nullable = false)
    private ClassShifts shift;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "is_active", nullable = false, columnDefinition = "BOOL DEFAULT true")
    private Boolean isActive;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_teacher_id"))
    private Teacher teacher;

    @Column(name = "teacher_id", insertable = false, updatable = false)
    private UUID teacherId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "course_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_course_id"))
    private Course course;

    @Column(name = "course_id", insertable = false, updatable = false)
    private UUID courseId;

    @OneToMany(mappedBy = "courseClass")
    private Collection<StudentClass> studentsClasses;

    public CourseClass() {
        this.isActive = true;
    }

}
