package com.saesig.templateManage;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TemplateManageMapper {
    List<TemplateManageDto> selectTemplateList(TemplateManageDto tmd);

    TemplateManageDto selectTemplate(Long id);

    int insertForm(TemplateManageDto tmd);

    int updateForm(TemplateManageDto tmd);
}
