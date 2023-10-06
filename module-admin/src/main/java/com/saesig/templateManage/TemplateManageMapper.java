package com.saesig.templateManage;

import com.saesig.domain.templateManage.SendCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TemplateManageMapper {
    List<TemplateManageDto> selectTemplateList(TemplateManageDto tmd);

    List<TemplateManageDto> selectTemplateListByCategory(SendCategory category);

    TemplateManageDto selectTemplate(Long id);

    int insertForm(TemplateManageDto tmd);

    int updateForm(TemplateManageDto tmd);

    int deleteItems(@Param("ids") Long[] ids);

}

