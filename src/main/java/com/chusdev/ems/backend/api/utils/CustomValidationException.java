package com.chusdev.ems.backend.api.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomValidationException extends RuntimeException {
    private final String fieldName;
    private final String message;

    public CustomValidationException(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }    
}
