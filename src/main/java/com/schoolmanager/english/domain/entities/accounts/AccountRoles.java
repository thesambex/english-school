package com.schoolmanager.english.domain.entities.accounts;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public enum AccountRoles {
    ADMIN(Sets.newHashSet()),
    EMPLOYEE(Sets.newHashSet()),
    STUDENT(Sets.newHashSet()),
    TEACHER(Sets.newHashSet());

    private final Set<AccountAuthorities> authorities;

    // TODO:GET AUTHORITIES

}
