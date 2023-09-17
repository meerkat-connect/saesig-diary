package com.saesig.faq;

import com.saesig.domain.faq.Faq;
import com.saesig.domain.faq.FaqCategory;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class FaqInsertDto {
    @NotEmpty(message = "제목을 입력해 주십시오.")
    private String title;
    @NotEmpty(message = "내용을 입력해 주십시오.")
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
