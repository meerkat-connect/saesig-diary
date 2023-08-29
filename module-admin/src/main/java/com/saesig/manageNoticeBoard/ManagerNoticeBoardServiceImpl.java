package com.saesig.manageNoticeBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ManagerNoticeBoardServiceImpl implements ManagerNoticeBoardService {

    @Autowired
    private ManagerNoticeBoardMapper managerNoticeBoardMapper;
    @Override
    public List<ManagerNoticeBoardDto> selectManagerNoticeBoardList(ManagerNoticeBoardDto mnbd) throws Exception {
        return managerNoticeBoardMapper.selectManagerNoticeBoardList(mnbd);
    }

    @Override
    public ManagerNoticeBoardDto selectManagerNoticeBoard(Long id) throws Exception {
        return managerNoticeBoardMapper.selectManagerNoticeBoard(id);
    }

    @Override
    @Transactional
    public int insertForm(ManagerNoticeBoardDto mnbd) throws Exception {
        return managerNoticeBoardMapper.insertForm(mnbd);
    }

    @Override
    @Transactional
    public int updateForm(ManagerNoticeBoardDto mnbd) throws Exception {
        return managerNoticeBoardMapper.updateForm(mnbd);
    }

    @Override
    @Transactional
    public int deleteItems(Long[] ids) throws Exception {
        return managerNoticeBoardMapper.deleteItems(ids);
    }
}
