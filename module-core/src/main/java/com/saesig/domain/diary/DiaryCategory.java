package com.saesig.domain.diary;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DiaryCategory implements EnumMapperType{
    SAESIGDIARY("새식일기"),
    MISSING("실종/발견");

    @Getter
    private final String value;

    public String getKey() {
        return name();
    }

    @JsonCreator
    public static DiaryCategory from(String sub) {
        return Stream.of(DiaryCategory.values())
                .filter(category -> category.toString().equals(sub.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
}
