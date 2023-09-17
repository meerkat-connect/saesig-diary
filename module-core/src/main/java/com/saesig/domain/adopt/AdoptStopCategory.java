package com.saesig.domain.adopt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
public enum AdoptStopCategory implements EnumMapperType {
    TYPE_0("관리자 분양중지"), TYPE_1("사용자 분양중지"), NULL("값 없음");

    @Getter
    private final String value;

    public String getKey() {
        return name();
    }

    @JsonCreator
    public static AdoptStopCategory from(String sub) {
        if (sub == null) {
            return NULL; // 추가한 NULL 상수 반환
        }
        return Stream.of(AdoptStopCategory.values())
                .filter(category -> category.toString().equals(sub.toUpperCase()))
                .findFirst()
                .orElse(null);
    }

}
