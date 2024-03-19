package com.schoolmanager.english.domain.entities.accounts;

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
@Table(name = "accounts", schema = "accounts")
@AllArgsConstructor
@Getter
@Setter
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username", nullable = false, length = 15, unique = true)
    private String username;

    @Column(name = "password", nullable = false, length = 72)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_role", nullable = false)
    private AccountRoles role;

    @Column(name = "is_active", nullable = false, columnDefinition = "BOOL DEFAULT true")
    private Boolean isActive;

    @Column(name = "is_locked", nullable = false, columnDefinition = "BOOL DEFAULT false")
    private Boolean isLocked;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(columnDefinition = "person_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_person_id"))
    private Person person;

    public Account() {
        this.isActive = true;
        this.isLocked = false;
    }

}
