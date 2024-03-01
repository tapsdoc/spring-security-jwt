package com.example.Spring.SecurityAlibou.commons;


import lombok.Getter;

@Getter
public enum Permission {

    MANAGER_READ("management:read"),
    MANAGER_WRITE("management:write"),
    MANAGER_UPDATE("management:update"),
    MANAGER_DELETE("management:delete"),

    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete");
    private final String permission;

    Permission (String permission) {
        this.permission = permission;
    }
}
