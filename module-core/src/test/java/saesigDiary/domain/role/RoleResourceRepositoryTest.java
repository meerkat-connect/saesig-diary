package saesigDiary.domain.role;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import saesigDiary.config.CustomDataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@CustomDataJpaTest
@ActiveProfiles("local")
class RoleResourceRepositoryTest {
    @Autowired
    private RoleResourceRepository roleResourceRepository;

    @Test
    @DisplayName("역할 및 자원 아이디로 조회")
    void 역할_및_자원_아이디로_조회(){
        //given
        Long roleId = 1L;
        Long[] resourcesId = new Long[]{1L,2L};

        //when
        List<RoleResource> mappedResourcesByRoleIdAndIn = roleResourceRepository.findAllByRoleIdAndResourceIdIn(roleId, List.of(resourcesId));

        //then
        mappedResourcesByRoleIdAndIn.stream().forEach(System.out::println);
    }

}