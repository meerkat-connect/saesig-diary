package com.saesig.faq;

import com.saesig.domain.faq.FaqCategory;
import lombok.Getter;

@Getter
public class FaqUpdateDto {
    private String title;
    private String content;
    private FaqCategory category;
    private Character isEnabled;
}
