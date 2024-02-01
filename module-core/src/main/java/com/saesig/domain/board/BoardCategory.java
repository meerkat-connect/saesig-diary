package com.saesig.domain.board;

import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BoardCategory implements EnumMapperType {
    NORMAL("일반")
    , ADMIN("관리자");

    @Getter
    private final String value;

    @Override
    public String getKey() {
        return name();
    }
}
