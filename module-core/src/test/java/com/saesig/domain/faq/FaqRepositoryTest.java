package com.saesig.domain.faq;

import com.saesig.config.CustomDataJpaTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@CustomDataJpaTest
@ActiveProfiles("local")
class FaqRepositoryTest {
    @Autowired
    private FaqRepository faqRepository;

    @Test
    @DisplayName("FAQ 전체 조회")
    void FAQ_전체_조회() {
        //given

        //when
        List<Faq> findAllFaq = faqRepository.findAll();

        //then
        assertThat(findAllFaq).isNotNull();
    }

    @Test
    @DisplayName("MEMBER 조인")
    void MEMBER_조인() {
        //given
        Integer start = 0;
        Integer length = 10;

        Integer pageNum = start / length;

        PageRequest of = PageRequest.of(pageNum, length);

        //when
        Page<Faq> allWithMemberJoin = faqRepository.findAllWithMember(of);

        //then
        assertThat(allWithMemberJoin).isNotNull();

    }

    @Test
    @DisplayName("max ord 조회")
    void max_ord_조회() {
        //given

        //when
        Long maxOrd = faqRepository.getMaxOrd();

        //then
        assertThat(maxOrd).isNotNull();
    }

    @Test
    @DisplayName("동적_쿼리(단일 + 사용여부)")
    void 동적_쿼리_단일_사용여부() {
        //given
        Character isEnabled = 'N';
        Specification<Faq> spec = (Root<Faq> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return null;
        };
        spec = spec.and(FaqSpecification.isEnabled(isEnabled));

        //when
        List<Faq> all = faqRepository.findAll(spec);

        //then
        assertThat(all).isNotNull();
    }

    @Test
    @DisplayName("동적_쿼리(단일 + 내용)")
    void 동적_쿼리_단일_내용() {
        //given
        String content = "content";
        Specification<Faq> spec = (Root<Faq> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return null;
        };
        spec = spec.and(FaqSpecification.hasContent(content));

        //when
        List<Faq> all = faqRepository.findAll(spec);

        //then
        assertThat(all).isNotNull();
    }

    @Test
    @DisplayName("동적_쿼리(단일 + 페이징)")
    void 동적_쿼리_단일_페이징() {
        //given
        String content = "content";
        Integer start = 0;
        Integer length = 10;
        Pageable pageable = PageRequest.of(start, length);

        Specification<Faq> spec = (Root<Faq> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return null;
        };
        spec = spec.and(FaqSpecification.hasContent(content));

        //when
        Page<Faq> all = faqRepository.findAll(spec, pageable);

        //then
        List<Faq> faqs = all.getContent();
        long count = all.getTotalElements();

        assertThat(faqs).isNotNull();
        assertThat(count).isGreaterThan(0L);
    }


}