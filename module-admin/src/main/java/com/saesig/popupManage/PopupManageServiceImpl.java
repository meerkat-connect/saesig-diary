package com.saesig.popupManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PopupManageServiceImpl implements PopupManageService {
    @Autowired
    private PopupManageMapper popupManageMapper;

    @Override
    public List<PopupManageDto> selectPopupList(PopupManageDto pmd) throws Exception {
        return popupManageMapper.selectPopupList(pmd);
    }

    @Override
    public PopupManageDto selectPopup(Long id) throws Exception {
        return popupManageMapper.selectPopup(id);
    }

    @Override
    public int selectOrd() throws Exception {
        return popupManageMapper.selectOrd();
    }

    @Override
    @Transactional
    public int changeIsEnabled(PopupManageDto pmd) throws Exception {
        return popupManageMapper.changeIsEnabled(pmd);
    }

    @Override
    @Transactional
    public int insertForm(PopupManageDto pmd) throws Exception {
        return popupManageMapper.insertForm(pmd);
    }

    @Override
    @Transactional
    public int updateForm(PopupManageDto pmd) throws Exception {
        return popupManageMapper.updateForm(pmd);
    }

    @Override
    @Transactional
    public int deleteItem(Long id) throws Exception {
        return popupManageMapper.deleteItem(id);
    }

    @Override
    public int updatePopupSort(PopupManageDto pmd) throws Exception {
        return popupManageMapper.updatePopupSort(pmd);
    }
}
