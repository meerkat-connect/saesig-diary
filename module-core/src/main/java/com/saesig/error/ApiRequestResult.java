package com.saesig.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// API 통신시 반환되는 공통 클래스
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiRequestResult {
    private boolean isSuccess;
    private Object response;
    private ErrorResponse error; // code, data , message

    private ApiRequestResult(boolean isSuccess, Object response, ErrorResponse error) {
        this.isSuccess = isSuccess;
        this.response = response;
        this.error = error;
    }

    // 성공 응답시
    public static ApiRequestResult of(Object response) {
        return new ApiRequestResult(true, response, null);
    }

    // 에러 응답시
    public static ApiRequestResult of(ErrorResponse error) {
        return new ApiRequestResult(false, null, error);
    }
}
