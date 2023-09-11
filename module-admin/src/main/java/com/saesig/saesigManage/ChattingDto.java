package com.saesig.saesigManage;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.common.mybatis.RequestDto;
import com.saesig.domain.adopt.AdoptStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ChattingDto extends RequestDto {
    private Long id;
    private String title;
    private String chatId;
    private String adoptId;
    private String memberId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    private Long createdBy;
    private AdoptStatus adoptStatus;
}
