package com.linguaai.user.exception;

import lombok.Getter;

@Getter
public class RoleAlreadyExistsException extends RuntimeException {
    private final String parameter;

    public RoleAlreadyExistsException(String parameter) {
        super("Role already exists with: " + parameter);
        this.parameter = parameter;
    }
}
