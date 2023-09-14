package com.saesig.saesigManage;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.common.mybatis.RequestDto;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ChatOpenReasonDto extends RequestDto {
    private Long id;
    private Long memberId;
    private String password;
    private String category;
    private String etcReason;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime modifiedAt;
    private String modifiedBy;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    private String createdBy;
}
