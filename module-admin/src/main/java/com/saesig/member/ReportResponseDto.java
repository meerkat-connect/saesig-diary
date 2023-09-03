package com.saesig.member;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReportResponseDto {
    private String nickname;
    private String email;
    private String content;
    private String category;
    private LocalDateTime reportedAt;
}
