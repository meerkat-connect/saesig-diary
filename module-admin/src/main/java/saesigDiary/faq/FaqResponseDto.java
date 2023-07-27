package saesigDiary.faq;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import saesigDiary.domain.faq.Faq;
import saesigDiary.domain.faq.FaqCategory;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class FaqResponseDto {
    private Long id;
    private String title;
    private String content;
    private FaqCategory category;
    private Long ord;
    private Character isEnabled;
    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    public FaqResponseDto(Faq faq) {
        this.id = faq.getId();
        this.title = faq.getTitle();
        this.content = faq.getContent();
        this.category = faq.getCategory();
        this.ord = faq.getOrd();
        this.isEnabled = faq.getIsEnabled();
        this.createdAt = faq.getCreatedAt();
        this.createdBy = faq.getCreatedBy().getNickname();
    }
}
