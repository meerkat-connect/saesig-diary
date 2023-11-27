package com.saesig.diaryManage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiaryCommentDto {
    private Long id;
    private Long diaryId;
    private Integer upperId;
    private String content;
    private String isDeleted;
    private int depth;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;
    private Long modifiedBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private Long createdBy;
    private int likeCnt;
    private String createdByName;
    private int replyCnt;

}
