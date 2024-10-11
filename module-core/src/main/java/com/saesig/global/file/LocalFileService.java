package com.saesig.global.file;

import com.saesig.domain.file.FileGroup;
import com.saesig.error.ErrorCode;
import com.saesig.error.FileNotExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class LocalFileService implements FileService {
    @Value("${file.dir}")
    private String fileDir;

    private final FileRepository fileRepository;

    private final FileGroupRepository fileGroupRepository;

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    @Override
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

    @Override
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
            log.error(ex.getMessage());
            throw new RuntimeException("파일 저장에 실패하였습니다.");
        }
    }

    @Override
    public FileDto findBySavedName(String savedFileName) {
        com.saesig.domain.file.File file = fileRepository.findBySavedName(savedFileName)
                .orElseThrow(() -> new FileNotExistException(ErrorCode.INVALID_INPUT_VALUE));

        return FileDto.builder()
                .originName(file.getOriginName())
                .savedName(file.getSavedName())
                .path(file.getFileGroup().getPath())
                .id(file.getId())
                .groupId(file.getFileGroup().getId())
                .build();
    }
}
