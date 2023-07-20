package saesigDiary.faq;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import saesigDiary.SaesigDiaryApplication;

import java.util.List;

@SpringBootTest(classes = SaesigDiaryApplication.class)
@ActiveProfiles("local")
class FaqServiceTest {
    @Autowired
    FaqService faqService;

    @Test
    @DisplayName("FAQ 전체 조회")
    void FAQ_전체_조회() {
        //given

        //when
        List<FaqResponseDto> all = faqService.findAll();

        //then
        all.stream().forEach(System.out::println);

    }

}