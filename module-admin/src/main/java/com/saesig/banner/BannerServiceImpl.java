package com.saesig.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<BannerDto> selectBannerList(BannerDto bd) throws Exception {
        return bannerMapper.selectBannerList(bd);
    }

    @Override
    public BannerDto selectBanner(Long id) throws Exception {
        return bannerMapper.selectBanner(id);
    }

    @Override
    public int selectOrd() throws Exception {
        return bannerMapper.selectOrd();
    }

    @Override
    @Transactional
    public int changeIsEnabled(BannerDto bd) throws Exception {
        return bannerMapper.changeIsEnabled(bd);
    }

    @Override
    @Transactional
    public int insertForm(BannerDto bd) throws Exception {
        return bannerMapper.insertForm(bd);
    }

    @Override
    @Transactional
    public int updateForm(BannerDto bd) throws Exception {
        return bannerMapper.updateForm(bd);
    }

    @Override
    @Transactional
    public int deleteItem(Long id) throws Exception {
        return bannerMapper.deleteItem(id);
    }

    @Override
    public int updateBannerSort(BannerDto bd) throws Exception {
        return bannerMapper.updateBannerSort(bd);
    }
}
