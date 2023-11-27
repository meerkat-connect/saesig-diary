package com.saesig.diaryManage;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface DiaryManageService {
    List<DiaryManageDto> selectDiaryList(DiaryManageDto param) throws Exception;

    DiaryManageDto selectDiaryCntData(DiaryManageDto param) throws Exception;

    DiaryManageDto selectDiaryById(DiaryManageDto param) throws Exception;

    List<DiaryTagDto> selectDiaryTagListById(DiaryManageDto param) throws Exception;

    Long updateDiaryInfo(DiaryManageDto param) throws Exception;

    List<DiaryCommentDto> selectDiaryCommentById(DiaryManageDto param) throws Exception;

    Long deleteDiary(Long[] ids) throws Exception;

    Long deleteDiaryComment(DiaryCommentDto param) throws Exception;


}
