package com.saesig.faq;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.saesig.domain.faq.Faq;
import com.saesig.domain.faq.FaqRepository;
import com.saesig.role.DataTablesResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FaqService {
    private final FaqRepository faqRepository;

    public List<FaqResponseDto> findAll() {
        return faqRepository.findAll().stream()
                .map(FaqResponseDto::new)
                .collect(Collectors.toList());
    }

    public DataTablesResponseDto findAll(Pageable pageable) {
        Page<Faq> findAllUsingPageable = faqRepository.findAllWithMember(pageable);
        return new DataTablesResponseDto(
                findAllUsingPageable,
                findAllUsingPageable.stream().map(FaqResponseDto::new).collect(Collectors.toList()));
    }

    public FaqResponseDto findById(Long id) {
        Faq faq = faqRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("FAQ 아이디가 존재하지 않습니다."));
        return new FaqResponseDto(faq);
    }
}
