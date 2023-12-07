package com.saesig.error;

import lombok.Getter;

@Getter
public class VerificationCodeMismatchException extends RuntimeException {
    private final ErrorCode errorCode;

    public VerificationCodeMismatchException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
