package saesigDiary.inquiry;

import lombok.Data;

@Data
public class InquiryAnswerDto {
    private Long id;
    private Long inquiry_id;
    private String title;
    private String content;
    private String modified_at;
    private String modified_by;
    private String created_at;
    private String created_by;
}
