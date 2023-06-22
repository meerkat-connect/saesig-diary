package saesigDiary.inquiry;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class InquiryDto {
    private Long id;
    private InquiryCategory category;
    private String categoryStr;
    private String incomingEmail;
    private InquiryStatus status;
    private String statusStr;
    private String title;
    private String content;
    private String isDeleted;
    private String createdAt;
    private Long createdBy;
    private String modifiedAt;
    private Long modifiedBy;
    private String nickname;
    private String memberId;
}
