package com.saesig.api.faq;

import com.saesig.domain.faq.Faq;
import com.saesig.domain.faq.FaqCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FaqApiResponseDto {
    private Long id;
    private String title;
    private String content;
    private FaqCategory category ;
    private Long ord;
    private Character isEnabled;

    public FaqApiResponseDto(Faq faq) {
        this.id = faq.getId();
        this.title = faq.getTitle();
        this.content = faq.getContent();
        this.category = faq.getCategory();
        this.ord = faq.getOrd();
        this.isEnabled = faq.getIsEnabled();
    }
}
