package com.saesig.domain.inquiry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@RequiredArgsConstructor
public enum InquiryStatus implements EnumMapperType{
    INCOMPLETE("답변대기"), COMPLETE("답변완료");

    @Getter
    private final String value;

    public String getKey() {
        return name();
    }

    @JsonCreator
    public static InquiryStatus from(String sub) {
        return Stream.of(InquiryStatus.values())
                .filter(category -> category.toString().equals(sub.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
}
