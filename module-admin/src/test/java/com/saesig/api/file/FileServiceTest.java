package com.saesig.api.file;

import com.saesig.global.file.FileDto;
import com.saesig.global.file.FileService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class FileServiceTest {
    @Autowired
    private FileService fileService;

    @Test
    @DisplayName("멀티파일 업로드 테스트")
    void 멀티파일_업로드_테스트() throws Exception {
        //given
        String fileName = "test";
        String extension = "txt";
        MockMultipartFile mockMultipartFile = new MockMultipartFile("dummy", fileName + "." + extension, "text/plain", "foo".getBytes());

        //when
        FileDto fileDto = fileService.storeFile(mockMultipartFile);

        //then
    }
}