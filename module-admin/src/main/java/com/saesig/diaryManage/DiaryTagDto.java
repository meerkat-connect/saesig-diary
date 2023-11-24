package com.saesig.diaryManage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiaryTagDto {
    Long diaryId;
    Long tagId;
    String tagName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime modifiedAt;
    private Long modifiedBy;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    private Long createdBy;
}
