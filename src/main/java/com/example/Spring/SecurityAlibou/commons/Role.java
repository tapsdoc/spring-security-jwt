package com.example.Spring.SecurityAlibou.commons;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.Spring.SecurityAlibou.commons.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER(Collections.emptySet()),

    SUPER_ADMIN(Set.of(
        ADMIN_READ,
        ADMIN_DELETE,
        ADMIN_WRITE,
        ADMIN_UPDATE,
        MANAGER_READ,
        MANAGER_WRITE,
        MANAGER_DELETE,
        MANAGER_UPDATE
    )),

    ADMIN(Set.of(
        ADMIN_READ,
        ADMIN_DELETE,
        ADMIN_WRITE,
        ADMIN_UPDATE
    )),

    MANAGER(Set.of(
        MANAGER_READ,
        MANAGER_WRITE,
        MANAGER_DELETE,
        MANAGER_UPDATE
    ));

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
