package com.saesig.managerBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ManagerBoardServiceImpl implements ManagerBoardService {

    @Autowired
    private ManagerBoardMapper managerNoticeBoardMapper;
    @Override
    public List<ManagerBoardDto> selectManagerBoardList(ManagerBoardDto mnbd) throws Exception {
        return managerNoticeBoardMapper.selectManagerBoardList(mnbd);
    }

    @Override
    public ManagerBoardDto selectManagerBoard(Long id) throws Exception {
        return managerNoticeBoardMapper.selectManagerBoard(id);
    }

    @Override
    @Transactional
    public int insertForm(ManagerBoardDto mnbd) throws Exception {
        return managerNoticeBoardMapper.insertForm(mnbd);
    }

    @Override
    @Transactional
    public int updateForm(ManagerBoardDto mnbd) throws Exception {
        return managerNoticeBoardMapper.updateForm(mnbd);
    }

    @Override
    @Transactional
    public int deleteItems(Long[] ids) throws Exception {
        return managerNoticeBoardMapper.deleteItems(ids);
    }
}
