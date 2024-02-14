package com.saesig.api.diary;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiaryMapper {

    List<DiaryDto> getDiaries(DiaryDto dd);

    int insertDiaryLike(DiaryDto dd);

    int deleteDiaryLike(DiaryDto dd);

    DiaryDto getDiary(DiaryDto dd);

    int insertDiary(DiaryDto dd);

    int deleteDiaryTag(DiaryDto dd);

    int insertDiaryTag(DiaryDto dd);

    int updateDiary(DiaryDto dd);

    int deleteDiary(DiaryDto dd);

    List<DiaryCommentDto> getComment(DiaryDto dd);

    int deleteComment(DiaryCommentDto dcd);

    int insertCommentLike(DiaryCommentDto dcd);

    int deleteCommentLike(DiaryCommentDto dcd);

    int updateComment(DiaryCommentDto dcd);

    int insertComment(DiaryCommentDto dcd);

    int insertDiaryReport(DiaryDto dd);
}
