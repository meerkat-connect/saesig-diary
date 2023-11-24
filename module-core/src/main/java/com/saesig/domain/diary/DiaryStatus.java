package com.saesig.domain.diary;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DiaryStatus implements EnumMapperType{
    POSTING("게시중"), STOP("중지"),DELETE("삭제");

    @Getter
    private final String value;

    public String getKey() {
        return name();
    }

    @JsonCreator
    public static DiaryStatus from(String sub) {
        return Stream.of(DiaryStatus.values())
                .filter(category -> category.toString().equals(sub.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
}
