package com.saesig.domain.templateManage;

import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SendCategory implements EnumMapperType {
    batch("배치")
    , now("즉시")
    , immediate("직접");

    @Getter
    private final String value;

    @Override
    public String getKey() {
        return name();
    }
}