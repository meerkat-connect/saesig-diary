package com.saesig.banner;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

    @Mapper
    public interface BannerMapper {
        List<BannerDto> selectBannerList(BannerDto bd) throws Exception;

        BannerDto selectBanner(Long id) throws Exception;

        int selectOrd() throws Exception;

        int changeIsEnabled(BannerDto bd) throws Exception;

        int insertForm(BannerDto bd) throws Exception;

        int updateForm(BannerDto bd) throws Exception;

        int deleteItem(Long id) throws Exception;

        int updateBannerSort(BannerDto bd) throws Exception;
    }

