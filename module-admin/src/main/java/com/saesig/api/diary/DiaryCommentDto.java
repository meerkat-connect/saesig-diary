package com.saesig.api.diary;

import com.saesig.config.auth.SessionMember;
import lombok.Data;

@Data
public class DiaryCommentDto {

    private SessionMember member;

    private Long id;
    private Long diaryId;
    private Long upperId;
    private Integer depth;
    private String content;
    private Long commentId;
    private char interestYn;
    private Integer ago;
}
