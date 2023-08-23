package com.saesig.sendHistory;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class SendHistoryDto {

    // history
    private String id;
    private String sendTemplateId;
    private String recipientId;
    private String nickname;
    private String recipientEmail;
    private String senderId;
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
}
