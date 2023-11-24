package com.saesig.domain.diary;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum WeatherCategory implements EnumMapperType {
    TYPE_A("유형A"),
    TYPE_B("유형B");

    @Getter
    private final String value;

    public String getKey() {
        return name();
    }

    @JsonCreator
    public static WeatherCategory from(String sub) {
        return Stream.of(WeatherCategory.values())
                .filter(category -> category.toString().equals(sub.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
}
