package com.saesig.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.domain.report.ReportCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReportResponseDto {
    private String nickname;

    private String email;

    private ReportCategory category;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reportedAt;

    public ReportResponseDto(String nickname, String email, String category, String content, Timestamp reportedAt) {
        this.nickname = nickname;
        this.email = email;
        this.content = content;
        this.category = ReportCategory.valueOf(category);
        this.reportedAt = reportedAt.toLocalDateTime();
    }
}
