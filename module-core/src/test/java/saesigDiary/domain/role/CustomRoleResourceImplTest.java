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
class CustomRoleResourceImplTest {
    @Autowired
    RoleResourceRepository roleResourceRepository;

    @Test
    @DisplayName("역할에 매핑된 자원 조회")
    void 역할에_매핑된_자원_조회() {
        //given
        Long roleId = 1L;

        //when
        List<RoleResourceResponseDto> mappedResources = roleResourceRepository.findMappedResources(roleId);

        //then
        assertThat(mappedResources).isNotNull();
    }
}