package com.saesig.domain.role;

import com.saesig.config.CustomDataJpaTest;
import com.saesig.global.menu.ResourceItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
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
        List<Resource> findAll = resourceRepository.findAll();

        //then
        assertThat(findAll.size()).isGreaterThan(0);
    }

    @Test
    public void changeDepth() {
        //given

        //when
        resourceRepository.changeDepth(2L);

        //then
    }

    @Test
    @DisplayName("enabled된 모든 자원 목록 조회 ")
    public void getEnabledResources(){
        //given

        //when
        List<ResourceItem> allEnabled = resourceRepository.findAllEnabled("admin");

        //then
        for (ResourceItem resource : allEnabled) {
            System.out.println(resource);
        }
    }

}