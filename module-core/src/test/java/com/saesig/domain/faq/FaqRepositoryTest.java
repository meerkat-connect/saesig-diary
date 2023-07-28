package com.saesig.domain.faq;

import com.saesig.domain.faq.Faq;
import com.saesig.domain.faq.FaqRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import com.saesig.config.CustomDataJpaTest;

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
    void MEMBER_조인(){
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


}