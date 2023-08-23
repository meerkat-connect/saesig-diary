package com.saesig.domain.templateManage;

import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SendMethod implements EnumMapperType {
    EMAIL("이메일")
    , SMS("SMS");

    @Getter
    private final String value;

    @Override
    public String getKey() {
        return name();
    }
}
