package com.saesig.inquiry;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.common.mybatis.RequestDto;
import com.saesig.domain.inquiry.InquiryCategory;
import com.saesig.domain.inquiry.InquiryStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class InquiryDto extends RequestDto {
    private Long id;
    private InquiryCategory category;
    private String categoryStr;
    private String incomingEmail;
    private InquiryStatus status;
    private String title;
    private String content;
    private String isDeleted;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;
    private Long createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifiedAt;
    private Long modifiedBy;
    private String nickname;
    private String memberId;

    private String searchStatus;
    private String searchCategory;
    private String searchTitle;
    private String searchKeywordSelect;
    private String searchKeyword;
}
