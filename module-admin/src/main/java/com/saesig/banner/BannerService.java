package com.saesig.banner;

import java.util.List;

public interface BannerService {
    List<BannerDto> selectManagerNoticeBoardList(BannerDto tmd) throws Exception;

    BannerDto selectManagerNoticeBoard(Long id) throws Exception;

    int insertForm(BannerDto tmd) throws Exception;

    int updateForm(BannerDto tmd) throws Exception;

    int deleteItems(Long[] ids) throws Exception;
}
