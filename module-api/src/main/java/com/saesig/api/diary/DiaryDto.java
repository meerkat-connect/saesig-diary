package com.saesig.api.diary;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.config.auth.SessionMember;
import com.saesig.global.file.FileDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DiaryDto {

    private SessionMember member;

    private Long id;
    private boolean isInterstYn;
    private Long memberId;
    private String nickname;
    private String title;
    private String content;
    private Long imageFileGroupId;
    private String weatherCategory;
    private String category;
    private String status;
    private String isSecret;
    private String isDeleted;
    private Long hits;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    private List<FileDto> fd;

    private String[] tagIds;
}
