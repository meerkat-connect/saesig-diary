package com.saesig.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.domain.adopt.AdoptStatus;
import com.saesig.domain.diary.DiaryStatus;
import com.saesig.domain.report.ReportCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponseDto {
    private Long id;

    private String type;

    private ReportCategory category;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd")
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

        private AdoptStatus status;

        private String createdBy;

        private AdoptStatus beforeStatus;

        private LocalDateTime beforeChangedAt;

        public AdoptDto(Long id, String content, String title, AdoptStatus status, String createdBy) {
            this.id = id;
            this.content = content;
            this.title = title;
            this.status = status;
            this.createdBy = createdBy;
        }
    }

    @Getter
    @ToString
    @AllArgsConstructor
    public static class DiaryDto {
        private Long id;

        private String title;

        private String content;

        private DiaryStatus status;

        private String createdBy;

        private DiaryStatus beforeStatus;

        private LocalDateTime beforeChangedAt;

        public DiaryDto(Long id, String title, String content, DiaryStatus status, String createdBy) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.status = status;
            this.createdBy = createdBy;
        }
    }
}
