package com.saesig.error;

import lombok.Getter;

@Getter
public class NicknameDuplicateException extends RuntimeException {
    private final ErrorCode errorCode;

    public NicknameDuplicateException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
