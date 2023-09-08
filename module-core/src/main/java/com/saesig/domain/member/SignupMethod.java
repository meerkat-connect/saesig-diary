package com.saesig.domain.member;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@RequiredArgsConstructor
public enum SignupMethod implements EnumMapperType {
    EMAIL("이메일")
    , NAVER("네이버")
    , GOOGLE("구글")
    , KAKAO("카카오");

    @Getter
    private final String value;

    public String getKey() {
        return name();
    }

    @JsonCreator
    public static SignupMethod from(String sub) {
        return Stream.of(SignupMethod.values())
                .filter(category -> category.toString().equals(sub.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
}
