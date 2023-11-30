package com.saesig.api.diary;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.config.auth.SessionMember;
import com.saesig.global.file.FileDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DiaryCommentDto {

    private SessionMember member;

    private Long id;
    private Long upperId;
    private Integer depth;
    private String content;
    private Long commentId;
    private char interestYn;
    private Integer ago;
}
