package com.saesig.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private String message;
    private String code;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ValidationError> validationErrors = new ArrayList<>();

    protected ErrorResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public static ErrorResponse of(String code, String message) {
        return new ErrorResponse(code, message);
    }

    public static ErrorResponse of(String code, String message, BindingResult bindingResult) {
        ErrorResponse errorResponse = new ErrorResponse(code, message);

        bindingResult
                .getFieldErrors()
                .stream()
                .forEach(fieldError -> errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage()));

        return errorResponse;
    }

    public void addValidationError(String target, String message) {
        validationErrors.add(new ValidationError(target, message));
    }

    @Getter
    @RequiredArgsConstructor
    private class ValidationError {
        private final String name;
        private final String message;

    }
}
