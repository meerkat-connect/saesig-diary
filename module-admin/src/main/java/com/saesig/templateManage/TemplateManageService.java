package com.saesig.templateManage;

import com.saesig.domain.templateManage.SendCategory;

import java.util.List;

public interface TemplateManageService {
    List<TemplateManageDto> selectTemplateList(TemplateManageDto tmd) throws Exception;

    List<TemplateManageDto> selectTemplateListByCategory(SendCategory category);

    TemplateManageDto selectTemplate(Long id) throws Exception;

    int insertForm(TemplateManageDto tmd) throws Exception;

    int updateForm(TemplateManageDto tmd) throws Exception;

    int deleteItems(Long[] ids) throws Exception;
}
