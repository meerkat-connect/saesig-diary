package com.saesig.api.adopt;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.config.auth.SessionMember;
import com.saesig.domain.report.ReportCategory;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdoptReportDto {
    private SessionMember member;

    private Long adoptId;
    private ReportCategory category;
    private String content;
    private Long memberId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime modifiedAt;
    private Long modifiedBy;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    private Long createdBy;
}
