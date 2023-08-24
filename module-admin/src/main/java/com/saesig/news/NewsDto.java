package com.saesig.news;

import com.saesig.domain.news.NewsCategory;
import lombok.Data;

@Data
public class NewsDto {
    private Long id;
    private NewsCategory category;
    private String title;
    private String content;
    private String isEnabled;
    private String isNotice;
    private String createdAt;
    private Long createdBy;
    private String modifiedAt;
    private Long modifiedBy;
    private String nickname;
    private String memberId;
}
