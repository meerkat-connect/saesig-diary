package com.saesig.domain.banner;

import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ExposureLocation implements EnumMapperType {
    main("메인")
    , sub1("서브1");

    @Getter
    private final String value;

    @Override
    public String getKey() {
        return name();
    }
}