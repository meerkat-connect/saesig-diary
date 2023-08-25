package com.saesig.domain.member;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@RequiredArgsConstructor
public enum MemberStatus implements EnumMapperType {
    NORMAL("정상"), LEAVE("탈퇴"), BANNED("정지"), DORMANCY("휴면");

    @Getter
    private final String value;

    public String getKey() {
        return name();
    }

    @JsonCreator
    public static MemberStatus from(String sub) {
        return Stream.of(MemberStatus.values())
                .filter(category -> category.toString().equals(sub.toUpperCase()))
                .findFirst()
                .orElse(null);
    }

}
