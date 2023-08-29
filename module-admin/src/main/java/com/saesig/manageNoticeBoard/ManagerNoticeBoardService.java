package com.saesig.manageNoticeBoard;

import java.util.List;

public interface ManagerNoticeBoardService {
    List<ManagerNoticeBoardDto> selectManagerNoticeBoardList(ManagerNoticeBoardDto tmd) throws Exception;

    ManagerNoticeBoardDto selectManagerNoticeBoard(Long id) throws Exception;

    int insertForm(ManagerNoticeBoardDto tmd) throws Exception;

    int updateForm(ManagerNoticeBoardDto tmd) throws Exception;

    int deleteItems(Long[] ids) throws Exception;
}
