package com.saesig.api.diary;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiaryMapper {

    List<DiaryDto> getDiaries(DiaryDto dd);
}
