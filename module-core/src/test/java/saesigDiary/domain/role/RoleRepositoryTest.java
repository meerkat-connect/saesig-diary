package saesigDiary.domain.role;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import saesigDiary.config.CustomDataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@CustomDataJpaTest
@ActiveProfiles("local")
class RoleRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;

    @DisplayName("역할 전체 목록 조회")
    @Test
    public void 역할_전체_목록_조회(){
        //given

        //when
        List<Role> findAll = roleRepository.findAll();

        //then
        assertThat(findAll.size()).isGreaterThanOrEqualTo(0);
    }
}