package com.saesig.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private String message;
    private String code;
    private Object detail;

    private ErrorResponse(String message, String code, Object detail) {
        this.message = message;
        this.code = code;
        this.detail = detail;
    }

    public static ErrorResponse of(String message, String code) {
        return new ErrorResponse(message, code, null);
    }

    public static ErrorResponse of(String code, String message, BindingResult bindingResult) {
        List<ValidationError> validationErrors = bindingResult.getFieldErrors().stream()
                .map(ValidationError::new)
                .collect(Collectors.toList());

        return new ErrorResponse(message, code, validationErrors);
    }

    @Getter
    private static class ValidationError {
        private final String name;
        private final String message;

        public ValidationError(FieldError fieldError) {
            this.name = fieldError.getField();
            this.message = fieldError.getDefaultMessage();
        }
    }
}
