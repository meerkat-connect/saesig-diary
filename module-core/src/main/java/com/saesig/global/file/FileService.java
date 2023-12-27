package com.saesig.global.file;

import com.saesig.domain.file.FileGroup;
import com.saesig.error.ErrorCode;
import com.saesig.error.FileNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    @Value("${file.dir}")
    private String fileDir;

    @Value("${favicon.file-dir}")
    private String faviconFileDir;

    @Value("${favicon.file-name}")
    private String faviconFileName;

    @Value("${kakao-thumbnail.file-dir}")
    private String kakaoThumbnailFileDir;

    @Value("${kakao-thumbnail.file-name")
    private String kakaoThumbnailFileName;

    private final FileRepository fileRepository;

    private final FileGroupRepository fileGroupRepository;

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    @Transactional
    public List<FileDto> storeFiles(List<MultipartFile> multipartFiles) {
        List<FileDto> storedFiles = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storedFiles.add(storeFile(multipartFile));
            }
        }

        return storedFiles;
    }

    @Transactional
    public FileDto storeFile(MultipartFile multipartFile) {
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
            multipartFile.transferTo(new File(getFullPath(savedFileName)));

            return FileDto.builder()
                    .savedName(savedFile.getSavedName())
                    .originName(savedFile.getOriginName())
                    .path(savedFileGroup.getPath())
                    .id(savedFile.getId())
                    .groupId(savedFileGroup.getId())
                    .build();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public FileDto findByName(String fileName) {
        com.saesig.domain.file.File file = fileRepository.findBySavedName(fileName)
                .orElseThrow(() -> new FileNotExistException(ErrorCode.INVALID_INPUT_VALUE));

        return FileDto.builder()
                .originName(file.getOriginName())
                .savedName(file.getOriginName())
                .path(file.getFileGroup().getPath())
                .id(file.getId())
                .groupId(file.getFileGroup().getId())
                .build();
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

    @Transactional
    public FileDto saveFavicon(MultipartFile multiPartFile) {

        try {
            if (multiPartFile.isEmpty()) {
                return null;
            }

            String originFileName = multiPartFile.getOriginalFilename();
            String savedFileName = createSavedFileName(originFileName);

            FileGroup savedFileGroup = fileGroupRepository.save(new FileGroup(fileDir));

            com.saesig.domain.file.File savedFile = fileRepository.save(com.saesig.domain.file.File.builder()
                    .fileGroup(savedFileGroup)
                    .originName(originFileName)
                    .savedName(savedFileName)
                    .size(multiPartFile.getSize())
                    .extension(extractExt(originFileName))
                    .build());
            multiPartFile.transferTo(new File(faviconFileDir + faviconFileName));

            return FileDto.builder()
                    .savedName(savedFile.getSavedName())
                    .originName(savedFile.getOriginName())
                    .path(savedFileGroup.getPath())
                    .id(savedFile.getId())
                    .groupId(savedFileGroup.getId())
                    .build();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Transactional
    public FileDto saveKakaoThumbnail(MultipartFile multiPartFile) {

        try {
            if (multiPartFile.isEmpty()) {
                return null;
            }

            String originFileName = multiPartFile.getOriginalFilename();
            String savedFileName = createSavedFileName(originFileName);

            FileGroup savedFileGroup = fileGroupRepository.save(new FileGroup(fileDir));

            com.saesig.domain.file.File savedFile = fileRepository.save(com.saesig.domain.file.File.builder()
                    .fileGroup(savedFileGroup)
                    .originName(originFileName)
                    .savedName(savedFileName)
                    .size(multiPartFile.getSize())
                    .extension(extractExt(originFileName))
                    .build());
            multiPartFile.transferTo(new File(kakaoThumbnailFileDir + kakaoThumbnailFileName));

            return FileDto.builder()
                    .savedName(savedFile.getSavedName())
                    .originName(savedFile.getOriginName())
                    .path(savedFileGroup.getPath())
                    .id(savedFile.getId())
                    .groupId(savedFileGroup.getId())
                    .build();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

}
