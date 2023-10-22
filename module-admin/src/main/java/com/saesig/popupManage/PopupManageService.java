package com.saesig.popupManage;

import java.util.List;

public interface PopupManageService {
    List<PopupManageDto> selectPopupList(PopupManageDto pmd) throws Exception;;

    PopupManageDto selectPopup(Long id) throws Exception;

    int selectOrd() throws Exception;

    int changeIsEnabled(PopupManageDto pmd) throws Exception;

    int insertForm(PopupManageDto pmd) throws Exception;

    int updateForm(PopupManageDto pmd) throws Exception;

    int deleteItem(Long id) throws Exception;

    int updatePopupSort(PopupManageDto pmd) throws Exception;
}
