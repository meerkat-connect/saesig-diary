package com.saesig.report;

import com.saesig.domain.adopt.AdoptStatus;
import com.saesig.domain.diary.DiaryStatus;
import com.saesig.domain.report.ReportCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
public class ReportResponseDto {
    private Long id;

    private ReportCategory category;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private String reporterName;

    private AdoptDto adoptDto;

    private DiaryDto diaryDto;

    @Getter
    @ToString
    @AllArgsConstructor
    public static class AdoptDto {
        private Long id;

        private String content;

        private String title;

        private AdoptStatus adoptStatus;

        private String createdBy;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    public static class DiaryDto {
        private Long id;

        private String title;

        private String content;

        private DiaryStatus diaryStatus;

        private String createdBy;
    }
}
