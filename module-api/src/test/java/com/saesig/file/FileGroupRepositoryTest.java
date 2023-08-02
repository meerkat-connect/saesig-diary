package com.saesig.file;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.saesig.domain.file.FileGroup;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
class FileGroupRepositoryTest {
    @Autowired
    private FileGroupRepository fileGroupRepository;

    @DisplayName("파일그룹정보 저장")
    @Test
    void 파일그룹정보_저장() {
        //given
        FileGroup fileGroup = new FileGroup("/test");

        //when
        FileGroup savedFileGroup = fileGroupRepository.save(fileGroup);

        //then
        FileGroup fileGroupById = fileGroupRepository.findById(savedFileGroup.getId()).get();
        assertThat(fileGroupById.getId()).isEqualTo(savedFileGroup.getId());
    }
}