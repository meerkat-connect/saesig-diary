package saesigDiary.domain.faq;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FaqCategory {
    TYPE_A("유형 A"),
    TYPE_B("유형 B"),
    TYPE_C("유형 C"),
    TYPE_D("유형 D"),
    TYPE_E("유형 E");

    @Getter
    private final String title;

    public String getCode() {
        return name();
    }
}
