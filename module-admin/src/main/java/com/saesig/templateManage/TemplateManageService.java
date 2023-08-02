package com.saesig.templateManage;

import java.util.List;

public interface TemplateManageService {
    List<TemplateManageDto> selectTemplateList(TemplateManageDto tmd) throws Exception;

    TemplateManageDto selectTemplate(Long id) throws Exception;

    int insertForm(TemplateManageDto tmd) throws Exception;

    int updateForm(TemplateManageDto tmd) throws Exception;
}
