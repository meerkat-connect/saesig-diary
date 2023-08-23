package com.saesig.domain.faq;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@RequiredArgsConstructor
public enum FaqCategory implements EnumMapperType {
    TYPE_A("유형 A"),
    TYPE_B("유형 B"),
    TYPE_C("유형 C"),
    TYPE_D("유형 D"),
    TYPE_E("유형 E");

    @Getter
    private final String value;

    public String getKey() {
        return name();
    }

    @JsonCreator
    public static FaqCategory from(String sub) {
        return Stream.of(FaqCategory.values())
                .filter(category -> category.toString().equals(sub.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
}
