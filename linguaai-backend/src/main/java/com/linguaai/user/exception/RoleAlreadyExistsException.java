package com.linguaai.user.exception;

import lombok.Getter;

@Getter
public class RoleAlreadyExistsException extends RuntimeException {
    private final String roleName;

    public RoleAlreadyExistsException(String name) {
        super("Role already exists: " + name);
        this.roleName = name;
    }
}
