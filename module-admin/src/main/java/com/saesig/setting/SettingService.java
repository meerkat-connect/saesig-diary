package com.saesig.setting;

import com.saesig.domain.file.FileGroup;
import com.saesig.global.file.FileDto;
import com.saesig.global.file.FileGroupRepository;
import com.saesig.global.file.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class SettingService {
    @Value("${kakao-thumbnail.file-dir}")
    private String kakaoThumbnailFileDir;

    @Value("${favicon.file-dir}")
    private String faviconFileDir;

    private final FileRepository fileRepository;

    private final FileGroupRepository fileGroupRepository;

    private final SettingRepository settingRepository;

    @Transactional
    public FileDto saveKakaoThumbnail(MultipartFile multipartFile) {
        return saveFile(multipartFile, kakaoThumbnailFileDir);
    }

    @Transactional
    public FileDto saveFavicon(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        String ext = extractExt(originalFilename);
        if(!"ico".equals(ext)) {
            throw new IllegalArgumentException("ico 확장자만 등록 가능합니다.");
        }

        return saveFile(multipartFile, faviconFileDir);
    }

    private FileDto saveFile(MultipartFile multipartFile, String fileDir) {
        try {
            if (multipartFile.isEmpty()) {
                return null;
            }

            String originFileName = multipartFile.getOriginalFilename();
            String savedFileName = createSavedFileName(originFileName);

            FileGroup savedFileGroup = fileGroupRepository.save(new FileGroup(fileDir));

            com.saesig.domain.file.File savedFile = fileRepository.save(com.saesig.domain.file.File.builder()
                    .fileGroup(savedFileGroup)
                    .originName(originFileName)
                    .savedName(savedFileName)
                    .size(multipartFile.getSize())
                    .extension(extractExt(originFileName))
                    .build());

            multipartFile.transferTo(new File(fileDir + savedFileName));

            return FileDto.builder()
                    .savedName(savedFile.getSavedName())
                    .originName(savedFile.getOriginName())
                    .path(savedFileGroup.getPath())
                    .id(savedFile.getId())
                    .groupId(savedFileGroup.getId())
                    .build();
        } catch (IOException ex) {
            log.error(ex.getMessage());
            throw new RuntimeException("파일 저장에 실패하였습니다.");
        }
    }

    public FileDto getKakaoThumbnail() {
        return getFile(kakaoThumbnailFileDir);
    }

    public FileDto getFavicon() {
        return getFile(faviconFileDir);
    }

    private FileDto getFile(String fileDir) {
        Optional<com.saesig.domain.file.File> fileOptional = settingRepository.findFile(fileDir);

        if (fileOptional.isPresent()) {
            com.saesig.domain.file.File file = fileOptional.get();

            return FileDto.builder()
                    .originName(file.getOriginName())
                    .savedName(file.getSavedName())
                    .path(file.getFileGroup().getPath())
                    .id(file.getId())
                    .groupId(file.getFileGroup().getId())
                    .build();
        }

        return null;
    }


    private String createSavedFileName(String originFileName) {
        String ext = extractExt(originFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originFileName) {
        int index = originFileName.lastIndexOf(".");

        return originFileName.substring(index + 1);
    }

}
