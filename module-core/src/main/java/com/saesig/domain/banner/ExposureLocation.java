package com.saesig.domain.banner;

import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ExposureLocation implements EnumMapperType {
    MAIN("메인")
    , EVENT("이벤트");

    @Getter
    private final String value;

    @Override
    public String getKey() {
        return name();
    }
}