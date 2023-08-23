package com.saesig.file;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.saesig.domain.file.File;
import com.saesig.domain.file.FileGroup;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@EnableJpaAuditing
class FileRepositoryTest {

    @Value("${file.dir}")
    private String path;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileGroupRepository fileGroupRepository;

    @DisplayName("파일정보 저장")
    @Test
    void 파일정보_저장() throws Exception {
        //given
        String fileName = "test";
        String extenstion = "txt";

        //when
        FileGroup fileGroup = new FileGroup(path);
        FileGroup savedFileGroup = fileGroupRepository.save(fileGroup);
        FileGroup fileGroupById = fileGroupRepository.findById(savedFileGroup.getId()).get();

        File file = File.builder()
                .fileGroup(fileGroupById)
                .originName(fileName + "." + extenstion)
                .savedName(UUID.randomUUID().toString())
                .extension(extenstion)
                .build();
        File savedFile = fileRepository.save(file);

        //then
        File fileById = fileRepository.findById(savedFile.getId()).get();
        assertThat(fileById.getFileGroup().getId()).isEqualTo(fileGroupById.getId());
        assertThat(fileById.getOriginName()).isEqualTo(fileName + "." + extenstion);
    }

}