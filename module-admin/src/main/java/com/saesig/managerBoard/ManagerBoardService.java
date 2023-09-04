package com.saesig.managerBoard;

import java.util.List;

public interface ManagerBoardService {
    List<ManagerBoardDto> selectManagerBoardList(ManagerBoardDto tmd) throws Exception;

    ManagerBoardDto selectManagerBoard(Long id) throws Exception;

    int insertForm(ManagerBoardDto tmd) throws Exception;

    int updateForm(ManagerBoardDto tmd) throws Exception;

    int deleteItems(Long[] ids) throws Exception;
}
