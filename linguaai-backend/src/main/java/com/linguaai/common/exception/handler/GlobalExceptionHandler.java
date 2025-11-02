package com.linguaai.common.exception.handler;

import com.linguaai.user.exception.RoleAlreadyExistsException;
import com.linguaai.user.exception.RoleNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RoleNotFoundException.class)
    public ProblemDetail handleRoleNotFound(RoleNotFoundException ex) {
        log.warn("Role not found: {}", ex.getRoleName(), ex);
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Role Not Found");
        problem.setDetail(ex.getMessage());
        problem.setProperty("errorCode", "ROLE_NOT_FOUND");
        problem.setProperty("roleParameter", ex.getRoleName());
        return problem;
    }

    @ExceptionHandler(RoleAlreadyExistsException.class)
    public ProblemDetail handleRoleAlreadyExists(RoleAlreadyExistsException ex) {
        log.warn("Role already exists: {}", ex.getParameter(), ex);
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problem.setTitle("Role Already Exists");
        problem.setDetail(ex.getMessage());
        problem.setProperty("errorCode", "ROLE_ALREADY_EXISTS");
        problem.setProperty("roleParameter", ex.getParameter());
        return problem;
    }
}