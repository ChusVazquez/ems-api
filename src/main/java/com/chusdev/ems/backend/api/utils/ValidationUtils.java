// src/main/java/com/chusdev/ems/backend/api/utils/ValidationUtils.java
package com.chusdev.ems.backend.api.utils;

import org.springframework.validation.BindingResult;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

public class ValidationUtils {

    public static ResponseEntity<?> handleValidationErrors(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    public static void validateField(boolean condition, String fieldName, String message) {
        if (condition) {
            throw new CustomValidationException(fieldName, message);
        }
    }
}
