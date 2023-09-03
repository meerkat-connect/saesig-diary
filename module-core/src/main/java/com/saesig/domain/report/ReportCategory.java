package com.saesig.domain.report;

import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReportCategory implements EnumMapperType {
    TYPE_A("유형1");

    @Getter
    private final String value;

    public String getKey() {
        return name();
    }



}
