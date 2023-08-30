package com.saesig.inquiry;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public enum InquiryCategory {
    PARCEL_OUT("분양"),ADOPTION("입양"),PROPOSAL("제안"),OTHER("기타");

    private final String title;
}
