package com.saesig.sendHistory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.common.mybatis.RequestDto;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class SendHistoryDto extends RequestDto {

    // history
    private Long id;
    private String sendTemplateId;
    private String recipientId;
    private String recipientNickname;
    private String recipientEmail;
    private String senderId;
    private String senderNickname;
    private String senderEmail;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendedAt;
    private String createdAt;
    private String createdBy;
    private String modifiedAt;
    private String modifiedBy;

    // template
    private String method;
    private String title;
    private String content;
    private String category;

    // search
    private String searchCategory;
    private String searchTitle;
    private String searchKeyword;
    private String searchBgngDt;
    private String searchEndDt;

}
