package com.saesig.api.faq;

import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class FaqApiRequestDto {
    private String keyword;
}
