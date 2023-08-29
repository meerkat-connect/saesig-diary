package com.saesig.banner;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BannerMapper {
    List<BannerDto> selectManagerNoticeBoardList(BannerDto tmd);

    BannerDto selectManagerNoticeBoard(Long id);

    int insertForm(BannerDto tmd);

    int updateForm(BannerDto tmd);

    int deleteItems(@Param("ids") Long[] ids);
}

