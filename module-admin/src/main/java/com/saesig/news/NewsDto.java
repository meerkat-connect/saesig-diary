package com.saesig.news;

import com.saesig.domain.news.NewsCategory;
import lombok.Data;

@Data
public class NewsDto {
    private Long id;
    private Long[] ids;
    private NewsCategory category;
    private String title;
    private String content;
    private String isDeleted;
    private String isFixed;
    private String createdAt;
    private Long createdBy;
    private String modifiedAt;
    private Long modifiedBy;
    private String nickname;
    private String memberId;
}
