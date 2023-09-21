package com.saesig.domain.report;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.domain.adopt.AdoptStopCategory;
import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@RequiredArgsConstructor
public enum ReportCategory implements EnumMapperType {
    TYPE_A("유형1");

    @Getter
    private final String value;

    public String getKey() {
        return name();
    }

    @JsonCreator
    public static ReportCategory from(String sub) {
        return Stream.of(ReportCategory.values())
                .filter(category -> category.toString().equals(sub.toUpperCase()))
                .findFirst()
                .orElse(null);
    }

}
