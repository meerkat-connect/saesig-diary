package saesigDiary.faq;

import lombok.Getter;
import lombok.ToString;
import saesigDiary.domain.faq.Faq;
import saesigDiary.domain.faq.FaqCategory;

import java.time.LocalDate;

@Getter
@ToString
public class FaqResponseDto {
    private Long id;
    private String title;
    private FaqCategory category;
    private Long ord;
    private Character isEnabled;
    private LocalDate createdAt;

    public FaqResponseDto(Faq faq) {
        this.id = faq.getId();
        this.title = faq.getTitle();
        this.category = faq.getCategory();
        this.ord = faq.getOrd();
        this.isEnabled = faq.getIsEnabled();
        this.createdAt = faq.getCreatedAt().toLocalDate();
    }
}
