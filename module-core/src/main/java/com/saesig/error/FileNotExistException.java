package com.saesig.error;

import lombok.Getter;

@Getter
public class FileNotExistException extends RuntimeException {
    private final ErrorCode errorCode;

    public FileNotExistException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
