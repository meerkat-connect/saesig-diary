package com.saesig.domain.policy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
public enum PolicyCategory implements EnumMapperType {
    TYPE_A("약관"),
    TYPE_B("정책");

    @Getter
    private final String value;

    public String getKey() {
        return name();
    }

    @JsonCreator
    public static PolicyCategory from(String sub) {
        return Stream.of(PolicyCategory.values())
                .filter(category -> category.toString().equals(sub.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
}
