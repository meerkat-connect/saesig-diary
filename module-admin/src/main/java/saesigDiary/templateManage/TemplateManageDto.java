package saesigDiary.templateManage;

import lombok.Data;

@Data
public class TemplateManageDto {

    private String id;
    private String method;
    private String title;
    private String content;
    private String category;
    private String is_enabled;
    private String time_point;
    private String created_at;
    private String created_by;
    private String modified_at;
    private String modified_by;
}
