package com.saesig.api.faq;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FaqApiService {
    private final FaqApiRepository faqApiRepository;

    public Page<FaqApiResponseDto> findAll(FaqApiRequestDto faqApiRequestDto) {
        Page<FaqApiResponseDto> find = faqApiRepository.findAll(faqApiRequestDto);

        return faqApiRepository.findAll(faqApiRequestDto);
    }
}
