package com.saesig.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper managerNoticeBoardMapper;
    @Override
    public List<BannerDto> selectManagerNoticeBoardList(BannerDto mnbd) throws Exception {
        return managerNoticeBoardMapper.selectManagerNoticeBoardList(mnbd);
    }

    @Override
    public BannerDto selectManagerNoticeBoard(Long id) throws Exception {
        return managerNoticeBoardMapper.selectManagerNoticeBoard(id);
    }

    @Override
    @Transactional
    public int insertForm(BannerDto mnbd) throws Exception {
        return managerNoticeBoardMapper.insertForm(mnbd);
    }

    @Override
    @Transactional
    public int updateForm(BannerDto mnbd) throws Exception {
        return managerNoticeBoardMapper.updateForm(mnbd);
    }

    @Override
    @Transactional
    public int deleteItems(Long[] ids) throws Exception {
        return managerNoticeBoardMapper.deleteItems(ids);
    }
}
