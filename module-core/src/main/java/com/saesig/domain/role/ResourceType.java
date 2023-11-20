package com.saesig.domain.role;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@RequiredArgsConstructor
public enum ResourceType implements EnumMapperType {
    DIRECTORY("디렉터리"), MENU("메뉴"), FUNCTION("기능"), MENU_DETAIL("메뉴 상세");

    @Getter
    private final String value;

    @Override
    public String getKey() {
        return name();
    }

    @JsonCreator
    public static ResourceType from(String sub) {
        return Stream.of(ResourceType.values())
                .filter(type -> type.toString().equals(sub.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
}
