package com.saesig.domain.inquiry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@RequiredArgsConstructor

public enum InquiryCategory implements EnumMapperType{
    PARCEL_OUT("분양"),ADOPTION("입양"),PROPOSAL("제안"),OTHER("기타");

    @Getter
    private final String value;

    public String getKey() {
        return name();
    }

    @JsonCreator
    public static InquiryCategory from(String sub) {
        return Stream.of(InquiryCategory.values())
                .filter(category -> category.toString().equals(sub.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
}
