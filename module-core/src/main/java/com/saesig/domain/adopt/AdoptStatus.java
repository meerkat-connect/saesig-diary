package com.saesig.domain.adopt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@RequiredArgsConstructor
public enum AdoptStatus implements EnumMapperType {
    ONGOING("분양중"), RESERVE("예약중"), COMPLETE("분양완료"), STOP("분양중지");

    @Getter
    private final String value;

    public String getKey() {
        return name();
    }

    @JsonCreator
    public static AdoptStatus from(String sub) {
        return Stream.of(AdoptStatus.values())
                .filter(category -> category.toString().equals(sub.toUpperCase()))
                .findFirst()
                .orElse(null);
    }

}
