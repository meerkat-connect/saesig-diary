package com.saesig.report;

import com.saesig.domain.report.ReportCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReportResponseDto {
    private Long id;

    private ReportCategory category;

    private Long memberId;
    
    private String memberName;

    private Long targetId;
    
    private String targetName;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private AdoptDto adoptDto;

    private DiaryDto diaryDto;

    @Getter
    @AllArgsConstructor
    private static class AdoptDto {
        private Long adoptId; // 분양 일련번호
        private String adoptContent;
        private String adoptTitle; // 제목

    }

    @Getter
    @AllArgsConstructor
    private static class DiaryDto {
        private Long diaryId; // 일기 일련번호
        private String diaryTitle;
        private String diaryContent; // 신고 내용
    }
}
