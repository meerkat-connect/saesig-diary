package saesigDiary.templateManage;

import java.util.List;

public interface TemplateManageService {
    List<TemplateManageDto> selectTemplateList(TemplateManageDto tmd) throws Exception;
}
