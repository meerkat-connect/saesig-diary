package saesigDiary.templateManage;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TemplateManageMapper {
    List<TemplateManageDto> selectTemplateList(TemplateManageDto tmd);
}
