package com.saesig.banner;

import java.util.List;
import java.util.Optional;

public interface BannerService {

    List<BannerDto> selectBannerList(BannerDto bd) throws Exception;;

    BannerDto selectBanner(Long id) throws Exception;

    int selectOrd() throws Exception;

    int changeIsEnabled(BannerDto bd) throws Exception;

    int insertForm(BannerDto bd) throws Exception;

    int updateForm(BannerDto bd) throws Exception;

    int deleteItem(Long id) throws Exception;

    int updateBannerSort(BannerDto bd) throws Exception;
}
