package saesigDiary.domain.role;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("local")
class ResourceRepositoryTest {
    @Autowired
    private ResourceRepository resourceRepository;

    @DisplayName("자원 전체 조회")
    @Test
    public void 자원_전체_조회() {
        List<Resource> resources = resourceRepository.findAll();

        assertThat(resources.size()).isGreaterThan(0);
    }
}