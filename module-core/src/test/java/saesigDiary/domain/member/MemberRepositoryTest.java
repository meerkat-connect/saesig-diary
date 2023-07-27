package saesigDiary.domain.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import saesigDiary.config.CustomDataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@CustomDataJpaTest
@ActiveProfiles("local")
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("페이징을 이용한 회원 목록 조회")
    void 페이징을_이용한_회원_목록_조회(){
        //given
        int page = 1;
        int size = 5;
        Pageable pageable = PageRequest.of(page,size);

        //when
        Page<Member> findAllUsingPageable = memberRepository.findAll(pageable);
        Pageable pageable1 = findAllUsingPageable.getPageable();

        //then
        assertThat(findAllUsingPageable).isNotNull();
    }

    @Test
    @DisplayName("회원 목록 전체 조회")
    void 회원_목록_전체_조회(){
        //given

        //when
        List<Member> findAll = memberRepository.findAll();

        //then
        assertThat(findAll).isNotNull();
    }



}