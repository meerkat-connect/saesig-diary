package com.saesig.inquiry;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum InquiryStatus {
    INCOMPLETE("답변대기"), COMPLETE("답변완료");

    private final String title;
}
