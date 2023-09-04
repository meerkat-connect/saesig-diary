package com.saesig.managerBoard;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ManagerBoardMapper {
    List<ManagerBoardDto> selectManagerBoardList(ManagerBoardDto tmd);

    ManagerBoardDto selectManagerBoard(Long id);

    int insertForm(ManagerBoardDto tmd);

    int updateForm(ManagerBoardDto tmd);

    int deleteItems(@Param("ids") Long[] ids);
}

