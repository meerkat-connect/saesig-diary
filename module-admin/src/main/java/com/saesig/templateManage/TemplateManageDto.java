package com.saesig.templateManage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class TemplateManageDto {

    private Long id;
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
    private String isEnabled;
    private String timePoint;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    private String createdBy;
    private String modifiedAt;
    private String modifiedBy;
}
