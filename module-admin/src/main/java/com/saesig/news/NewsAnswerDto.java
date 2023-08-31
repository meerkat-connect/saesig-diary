package com.saesig.news;

import lombok.Data;

@Data
public class NewsAnswerDto {
    private Long id;
    private Long inquiryId;
    private String title;
    private String content;
    private String modifiedAt;
    private Long modifiedBy;
    private String createdAt;
    private Long createdBy;
    private String createdByStr;
}
