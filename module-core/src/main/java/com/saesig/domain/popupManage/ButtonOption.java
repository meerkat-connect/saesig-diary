package com.saesig.domain.popupManage;

import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ButtonOption implements EnumMapperType {
    day("1일간 보지 않기")
    , week("7일간 보지 않기")
    , never("다시 보지 않기")
    , none("없음");


    @Getter
    private final String value;

    @Override
    public String getKey() {
        return name();
    }
}