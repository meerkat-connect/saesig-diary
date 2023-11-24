package com.saesig.diaryManage;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiaryManageMapper {
    List<DiaryManageDto> selectDiaryList(DiaryManageDto param) throws Exception;
    DiaryManageDto selectDiaryCntData(DiaryManageDto param) throws Exception;
    DiaryManageDto selectDiaryById(DiaryManageDto param) throws Exception;
    List<DiaryTagDto> selectDiaryTagListById(DiaryManageDto param) throws Exception;
    Long updateDiaryInfo(DiaryManageDto param) throws Exception;
}
