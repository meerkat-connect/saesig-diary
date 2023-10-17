package com.saesig.error;

import lombok.Getter;

@Getter
public class VerificationCodeMismatchException extends RuntimeException {
    private final String defaultMessage;

    private final String field;

    public VerificationCodeMismatchException(String defaultMessage, String field) {
        this.defaultMessage = defaultMessage;
        this.field = field;
    }
}
