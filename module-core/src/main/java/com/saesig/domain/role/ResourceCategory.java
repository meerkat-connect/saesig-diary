package com.saesig.domain.role;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;


@RequiredArgsConstructor
public enum ResourceCategory implements EnumMapperType {
    ADMIN("/admin", "관리자"), FRONT("/front", "사용자"), API("/api", "API");

    @Getter
    private final String prefix;

    @Getter
    private final String value;

    @Override
    public String getKey() {
        return name();
    }

    @JsonCreator
    public static ResourceCategory from(String sub) {
        return Stream.of(ResourceCategory.values())
                .filter(type -> type.toString().equals(sub.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
}

