package com.saesig.faq;

import com.saesig.common.RequestDto;
import lombok.Getter;

@Getter
public class FaqRequestDto extends RequestDto {
    private String content;
    private String title;
    private String category;
    private Character isEnabled;

    public FaqRequestDto(Integer start, Integer length, Integer pageNum, String searchType, String searchKeyword, String content, String title, String category, Character isEnabled) {
        super(start, length, pageNum, searchType, searchKeyword);
        this.content = content;
        this.title = title;
        this.category = category;
        this.isEnabled = isEnabled;
    }
}
