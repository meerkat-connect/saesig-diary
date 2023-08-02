package com.saesig.templateManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateManageServiceImpl implements TemplateManageService{

    @Autowired
    private TemplateManageMapper templateManageMapper;
    @Override
    public List<TemplateManageDto> selectTemplateList(TemplateManageDto tmd) throws Exception {
        return templateManageMapper.selectTemplateList(tmd);
    }

    @Override
    public TemplateManageDto selectTemplate(Long id) throws Exception {
        return templateManageMapper.selectTemplate(id);
    }
}
