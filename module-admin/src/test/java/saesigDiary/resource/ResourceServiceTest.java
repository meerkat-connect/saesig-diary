package saesigDiary.resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import saesigDiary.SaesigDiaryApplication;
import saesigDiary.domain.role.ResourceRepository;

import javax.transaction.Transactional;

@SpringBootTest(classes = SaesigDiaryApplication.class)
@ActiveProfiles("local")
public class ResourceServiceTest {

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
        ResourceResponseDto byId = resourceService.findById(3L);

        ResourceMoveDto testData = ResourceMoveDto.builder()
                                                .originalOrdOfSelectedNode(1)
                                                .originalParentIdOfSelectedNode(2L)
                                                .newOrdOfSelectedNode(2)
                                                .newParentIdOfSelectedNode(1L)
                                                .idOfSelectedNode(3L)
                                                .build();

        //given
        resourceService.move(testData);

        //then
        ResourceResponseDto byId1 = resourceService.findById(3L);
        System.out.println(byId);
        System.out.println(byId1);
    }

}