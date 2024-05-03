package com.saesig.api.faq;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FaqApiService {
    private final FaqApiRepository faqApiRepository;

    public Page<FaqApiResponseDto> findAll(FaqApiRequestDto faqApiRequestDto, Pageable pageable) {
        return faqApiRepository.findAll(faqApiRequestDto, pageable);
    }
}
