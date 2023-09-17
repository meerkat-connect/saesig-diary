package com.saesig.faq;

import org.springframework.data.domain.Page;

public interface CustomAdminFaqRepository {
    Page<FaqResponseDto> findAll(FaqRequestDto faqRequestDto);
}
