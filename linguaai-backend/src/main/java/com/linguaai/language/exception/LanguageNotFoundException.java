package com.linguaai.language.exception;

import lombok.Getter;

@Getter
public class LanguageNotFoundException extends RuntimeException {
    private final String parameter;

    public LanguageNotFoundException(String parameter) {
        super("Language with '" + parameter + "' does not exist.");
        this.parameter = parameter;
    }
}