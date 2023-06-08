package saesigDiary.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "COM-1", "서버에 문제가 발생하였습니다."),
    UNAUTHORIZED(401, "COM-2", "인증에 실패하였습니다."),
    FORBIDDEN(403, "COM-3", "권한이 없습니다."),
    INVALID_INPUT_VALUE(400, "COM-4", "입력값이 유효하지 않습니다.");

    private final int status;
    private final String code;
    private final String message;

}
