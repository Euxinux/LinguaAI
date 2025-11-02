package com.linguaai.user.exception;

import lombok.Getter;

@Getter
public class RoleNotFoundException extends RuntimeException {
    private final String roleName;

    public RoleNotFoundException(String roleName) {
        super("Role with '" + roleName + "' does not exist.");
        this.roleName = roleName;
    }
}