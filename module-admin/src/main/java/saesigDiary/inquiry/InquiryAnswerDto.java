package saesigDiary.inquiry;

import lombok.Data;

@Data
public class InquiryAnswerDto {
    private Long id;
    private Long inquiryId;
    private String title;
    private String content;
    private String modifiedAt;
    private Long modifiedBy;
    private String createdAt;
    private Long createdBy;
    private String createdByStr;
}
