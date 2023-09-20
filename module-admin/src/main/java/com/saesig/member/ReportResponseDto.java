package com.saesig.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReportResponseDto {
    private String nickname;
    private String email;
    private String category;
    private String content;
    private LocalDateTime reportedAt;

    public ReportResponseDto(String nickname, String email, String category, String content, Timestamp reportedAt) {
        this.nickname = nickname;
        this.email = email;
        this.content = content;
        this.category = category;
        this.reportedAt = reportedAt.toLocalDateTime();
    }
}
