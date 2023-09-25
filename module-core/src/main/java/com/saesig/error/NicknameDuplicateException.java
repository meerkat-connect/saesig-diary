package com.saesig.error;

import lombok.Getter;

@Getter
public class NicknameDuplicateException extends RuntimeException {
    private final String defaultMessage;

    private final String field;

    public NicknameDuplicateException(String defaultMessage, String field) {
        this.defaultMessage = defaultMessage;
        this.field = field;
    }
}
