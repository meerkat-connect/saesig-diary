package saesigDiary.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberStatus {
    NORMAL("정상"), LEAVE("탈퇴"), BANNED("정지"), DORMANCY("휴면");

    private final String title;

}
