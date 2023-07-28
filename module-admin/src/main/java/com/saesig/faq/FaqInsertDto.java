package com.saesig.faq;

import com.saesig.domain.faq.Faq;
import com.saesig.domain.faq.FaqCategory;
import lombok.Getter;

@Getter
public class FaqInsertDto {
    private String title;
    private String content;
    private FaqCategory category;
    private Long ord;
    private Character isEnabled;

    public Faq toEntity() {
        return Faq.builder()
                .title(this.title)
                .content(this.content)
                .category(this.category)
                .ord(this.ord)
                .isEnabled(this.isEnabled)
                .build();
    }

    public void setOrd(Long maxOrd) {
        this.ord = maxOrd;
    }
}
