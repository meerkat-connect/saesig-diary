package com.saesig.manageNoticeBoard;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ManagerNoticeBoardMapper {
    List<ManagerNoticeBoardDto> selectManagerNoticeBoardList(ManagerNoticeBoardDto tmd);

    ManagerNoticeBoardDto selectManagerNoticeBoard(Long id);

    int insertForm(ManagerNoticeBoardDto tmd);

    int updateForm(ManagerNoticeBoardDto tmd);

    int deleteItems(@Param("ids") Long[] ids);
}

