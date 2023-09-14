package com.saesig.saesigManage;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.common.mybatis.RequestDto;
import com.saesig.domain.report.ReportCategory;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
public class ReportingDto extends RequestDto {
    private Long id;
    private String adoptId;
    private ReportCategory category;
    private String content;
    private String memberId;
    private String nickname;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime modifiedAt;
    private Long modifiedBy;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    private Long createdBy;

}
