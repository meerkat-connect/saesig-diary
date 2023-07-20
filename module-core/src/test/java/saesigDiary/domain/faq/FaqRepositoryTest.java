package saesigDiary.domain.faq;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import saesigDiary.config.CustomDataJpaTest;

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

}