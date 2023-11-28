package com.saesig.api.diary;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.config.auth.SessionMember;
import com.saesig.domain.diary.DiaryCategory;
import com.saesig.domain.diary.DiaryStatus;
import com.saesig.domain.diary.WeatherCategory;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DiaryDto {
    private SessionMember member;

    private Long id;
    private String title;
    private String content;
    private Long imageFileGroupId;
    private WeatherCategory weatherCategory;
    private DiaryStatus diaryStatus;
    private DiaryCategory diaryCategory;
    private String isSecret;
    private String isDeleted;
    private Long hits;
    private Long tagGroupId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime modifiedAt;
    private Long modifiedBy;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    private Long createdBy;

    private String createdByName;
    private String modifiedByName;

    private Integer commentCnt;
    private Integer likeCnt;


    //searchParam
    private String searchKeyword;
    private String searchIsDeleted;
    private String searchTitle;
    private String searchStatus;
    private String searchCategory;

}
