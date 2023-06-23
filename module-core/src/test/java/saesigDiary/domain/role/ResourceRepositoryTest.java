package saesigDiary.domain.role;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import saesigDiary.config.CustomDataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@CustomDataJpaTest
@ActiveProfiles("local")
class ResourceRepositoryTest {
    @Autowired
    private ResourceRepository resourceRepository;

    @DisplayName("자원 전체 조회")
    @Test
    public void 자원_전체_조회() {
        //given

        //when
        List<Resource> resources = resourceRepository.findAll();

        //then
        assertThat(resources.size()).isGreaterThan(0);
    }

    @DisplayName("사용자 정의 메소드 조회")
    @Test
    public void 사용자_정의_메소드_조회() {
        //given

        //when
        List<Resource> allWithRecursive = resourceRepository.findAllWithRecursive();

        //then
        allWithRecursive.stream().forEach(resource -> System.out.println(resource));
    }

    @Test
    public void testCte() {
        //given

        //when
        List<ResourceCteDto> resources = resourceRepository.findAllByIdUsingCTE(2L);

        //then
        assertThat(resources.size()).isGreaterThan(0);
    }

    @Test
    public void changeDepth() {
        //given

        //when
        resourceRepository.changeDepth(2L);

        //then
    }
}