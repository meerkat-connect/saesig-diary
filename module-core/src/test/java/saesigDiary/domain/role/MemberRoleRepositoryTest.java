package saesigDiary.domain.role;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import saesigDiary.config.CustomDataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@CustomDataJpaTest
@ActiveProfiles("local")
class MemberRoleRepositoryTest {
    @Autowired
    private MemberRoleRepository memberRoleRepository;
    
    @Test
    @DisplayName("아이디 및 페이징 동시 사용 조회")
    void 아이디_및_페이징_동시_사용_조회(){
        //given
        Long roleId = 2l;
        PageRequest of = PageRequest.of(0,5);

        //when
        Page<MemberRole> byId = memberRoleRepository.findByRoleId(roleId, of);

        //then
        assertThat(byId).isNotNull();
    }
    
}