package com.saesig.templateManage;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.common.mybatis.RequestDto;
import com.saesig.config.auth.SessionMember;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class TemplateManageDto extends RequestDto {

    private SessionMember member;

    private Long id;
    private Long[] ids;
    private String method;
    private String title;
    private String content;
    private String category;
    private String isEnabled;
    private String timePoint;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    private String createdBy;
    private String modifiedAt;
    private String modifiedBy;

    // FILTER
    // 발송수단, 발송유형, 제목, 사용여부
    private String searchMethod;
    private String searchCategory;
    private String searchTitle;
    private String searchIsEnabled;

}
