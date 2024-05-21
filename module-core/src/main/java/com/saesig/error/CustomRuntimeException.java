package com.saesig.error;

import lombok.Getter;

@Getter
public class CustomRuntimeException extends RuntimeException{
    private ErrorCode errorCode;


    public CustomRuntimeException() {
    }

    public CustomRuntimeException(String message) {
        super(message);
    }

    public CustomRuntimeException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public CustomRuntimeException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
