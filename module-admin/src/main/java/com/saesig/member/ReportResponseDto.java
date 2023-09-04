package com.saesig.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReportResponseDto {
    private String nickname;
    private String email;
    private String content;
    private String category;
    private LocalDateTime reportedAt;

    public ReportResponseDto(String nickname, String email, String content, String category, LocalDateTime reportedAt) {
        this.nickname = nickname;
        this.email = email;
        this.content = content;
        this.category = category;
        this.reportedAt = reportedAt;
    }
}
