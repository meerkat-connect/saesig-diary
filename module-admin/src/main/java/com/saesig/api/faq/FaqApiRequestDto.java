package com.saesig.api.faq;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
public class FaqApiRequestDto {
    private int page = 0;
    private int size = 10;
    private String keyword;

    public Pageable getPageable() {
        return PageRequest.of(page,size);
    }
}
