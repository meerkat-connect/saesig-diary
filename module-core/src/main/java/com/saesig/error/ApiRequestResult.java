package com.saesig.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

// API 통신시 반환되는 공통 클래스
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiRequestResult {
    private boolean isSuccess;
    private Map<String, Object> data = new HashMap<>();
    private Map<String, Object> page = new HashMap<>();
    private ErrorResponse error;

    private ApiRequestResult(boolean isSuccess, Map<String, Object> data, ErrorResponse error, Map<String, Object> page) {
        this.isSuccess = isSuccess;
        this.data = data;
        this.error = error;
        this.page = page;
    }

    // 성공 응답시
    public static ApiRequestResult of(Map<String, Object> data) {
        return new ApiRequestResult(true, data, null, null);
    }

    // 에러 응답시
    public static ApiRequestResult of(ErrorResponse error) {
        return new ApiRequestResult(false, null, error, null);
    }

    public static ApiRequestResult of(Map<String, Object> data, Map<String, Object> page) {
        return new ApiRequestResult(false, data, null, page);
    }

    public static ApiRequestResult noContent() {
        return new ApiRequestResult(true,null,null,null);
    }
}


