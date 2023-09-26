package com.saesig.resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.saesig.SaesigDiaryApplication;
import com.saesig.domain.role.ResourceRepository;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = SaesigDiaryApplication.class)
@ActiveProfiles("local")
class ResourceServiceTest {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResourceRepository resourceRepository;

    @Test
    @DisplayName("자원 이동 테스트")
    @Transactional
    void 자원_이동_테스트() {
        //when
        // id 3 -> id 1의 마지막으로 이동하는 경우
        // id 3의 upperid : 2, newparentid : 1
        ResourceResponseDto byId = resourceService.findById(5L);

        ResourceMoveDto testData = ResourceMoveDto.builder()
                .originalOrdOfSelectedNode(2)
                .originalParentIdOfSelectedNode(3L)
                .newOrdOfSelectedNode(2)
                .newParentIdOfSelectedNode(1L)
                .depthOfNewParentId(1)
                .idOfSelectedNode(5L)
                .build();

        //given
        resourceService.move(testData);

        //then
        ResourceResponseDto byId1 = resourceService.findById(5L);
        List<ResourceResponseDto> findAll = resourceService.findAll();

        assertThat(byId.getUpperId()).isNotEqualTo(byId1.getUpperId());
        findAll.forEach(System.out::println);

    }

}