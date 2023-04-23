package saesigDiary.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SignupMethod {
    EMAIL("email")
    , SOCIAL("social")
    , SMS("sms");

    private final String title;
}
