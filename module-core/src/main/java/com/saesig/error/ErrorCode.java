package com.saesig.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COM-1", "서버에 문제가 발생하였습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COM-2", "인증에 실패하였습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COM-3", "권한이 없습니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "COM-4", "입력값이 유효하지 않습니다."),
    INVALID_VERIFICATION_CODE(HttpStatus.BAD_REQUEST, "COM-5", "인증번호가 일치하지 않습니다."),
    EMAIL_DUPLICATED(HttpStatus.BAD_REQUEST, "COM-6", "이메일이 중복됩니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    public static ErrorCode of(HttpStatus status) {
        return Stream.of(ErrorCode.values())
                .filter(type -> type.getStatus().equals(status))
                .findFirst()
                .orElse(null);
    }

}
