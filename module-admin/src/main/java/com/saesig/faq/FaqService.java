package com.saesig.faq;

import com.saesig.domain.faq.Faq;
import com.saesig.domain.faq.FaqRepository;
import com.saesig.domain.faq.FaqSpecification;
import com.saesig.role.DataTablesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.transaction.Transactional;
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

    public DataTablesResponseDto findAll(FaqRequestDto faqRequestDto) {
        Integer pageNum = faqRequestDto.getStart() / faqRequestDto.getLength();
        PageRequest of = PageRequest.of(pageNum, faqRequestDto.getLength(), Sort.by("ord"));

        Specification<Faq> spec = (root, query, criteriaBuilder) -> null;

        if (!StringUtils.isEmpty(faqRequestDto.getTitle())) {
            spec = spec.and(FaqSpecification.hasTitle(faqRequestDto.getTitle()));
        }

        if (!StringUtils.isEmpty(faqRequestDto.getCategory())) {
            spec = spec.and(FaqSpecification.hasCategory(faqRequestDto.getCategory()));
        }

        if (faqRequestDto.getIsEnabled() != null) {
            spec = spec.and(FaqSpecification.isEnabled(faqRequestDto.getIsEnabled()));
        }

        Page<Faq> findAllUsingPageable = faqRepository.findAll(spec, of);

        return new DataTablesResponseDto(
                findAllUsingPageable,
                findAllUsingPageable.stream().map(FaqResponseDto::new).collect(Collectors.toList()));
    }

    public FaqResponseDto findById(Long id) {
        Faq faq = faqRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("FAQ 아이디가 존재하지 않습니다."));
        return new FaqResponseDto(faq);
    }

    @Transactional
    public Long insert(FaqInsertDto faqInsertDto) {
        faqInsertDto.setOrd(faqRepository.getMaxOrd());
        Faq save = faqRepository.save(faqInsertDto.toEntity());
        return save.getId();
    }

    @Transactional
    public Long update(Long id, FaqUpdateDto faqUpdateDto) {
        Faq faq = faqRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("FAQ 아이디가 존재하지 않습니다."));

        faq.updateInfo(faqUpdateDto.getTitle()
                , faqUpdateDto.getContent()
                , faqUpdateDto.getCategory()
                , faqUpdateDto.getIsEnabled());

        return faq.getId();
    }

    @Transactional
    public void delete(Long[] deleteIds) {
        for (Long deleteId : deleteIds) {
            Faq faq = faqRepository.findById(deleteId).orElseThrow(() -> new IllegalArgumentException("FAQ 아이디가 존재하지 않습니다."));
            faq.delete();
        }
    }

    @Transactional
    public void move(Long id, String mode) {
        Faq faq = faqRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("FAQ 아이디가 존재하지 않습니다."));
        Faq by;

        if ("up".equals(mode)) {
            // 위로 이동
            by = faqRepository.findByOrd(faq.getOrd() - 1);
            faq.moveUp();
            by.moveDown();
        } else {
            // 아래로 이동
            by = faqRepository.findByOrd(faq.getOrd() + 1);
            faq.moveDown();
            by.moveUp();
        }
    }
}
