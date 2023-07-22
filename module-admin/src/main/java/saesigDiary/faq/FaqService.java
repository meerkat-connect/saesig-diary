package saesigDiary.faq;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import saesigDiary.domain.faq.FaqRepository;

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
}
