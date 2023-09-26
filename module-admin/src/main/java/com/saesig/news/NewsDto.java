package com.saesig.news;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.common.mybatis.RequestDto;
import com.saesig.domain.news.NewsCategory;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class NewsDto extends RequestDto {
    private Long id;
    private Long[] ids;
    private NewsCategory category;
    private String title;
    private String content;
    private String isDeleted;
    private String isFixed;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;
    private Long createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifiedAt;
    private Long modifiedBy;
    private String nickname;
    private String memberId;

    private String searchCategory;
    private String searchTitle;
}
