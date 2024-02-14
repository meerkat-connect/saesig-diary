package com.saesig.api.diary;

import java.util.List;

public interface DiaryService {

    List<DiaryDto> getDiaries(DiaryDto dd);

    int insertDiaryLike(DiaryDto dd);

    int deleteDiaryLike(DiaryDto dd);

    DiaryDto getDiary(DiaryDto dd);

    int insertDiary(DiaryDto dd);

    int updateDiary(DiaryDto dd);

    int deleteDiary(DiaryDto dd);

    List<DiaryCommentDto> getComment(DiaryDto dd);

    int insertComment(DiaryCommentDto dcd);

    int insertCommentLike(DiaryCommentDto dcd);

    int deleteCommentLike(DiaryCommentDto dcd);

    int updateComment(DiaryCommentDto dcd);

    int deleteComment(DiaryCommentDto dcd);

    int insertDiaryReport(DiaryDto dd);
}
