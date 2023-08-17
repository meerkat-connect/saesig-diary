package com.saesig.file;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.saesig.domain.file.FileGroup;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    @Value("${file.dir}")
    private String fileDir;

    private final FileRepository fileRepository;

    private final FileGroupRepository fileGroupRepository;

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    @Transactional
    public List<FileDto> storeFiles(List<MultipartFile> multipartFiles) throws Exception {
        List<FileDto> storedFiles = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storedFiles.add(storeFile(multipartFile));
            }
        }

        return storedFiles;
    }

    @Transactional
    public FileDto storeFile(MultipartFile multipartFile) throws IOException {

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
                .path(savedFileGroup.getDirectoryPath())
                .build();
    }

    public FileDto findByName(String fileName) throws NoSuchFileException {
        com.saesig.domain.file.File file = fileRepository.findBySavedName(fileName)
                .orElseThrow(() -> new NoSuchFileException("파일이 없습니다."));

        return FileDto.builder()
                .originName(file.getOriginName())
                .savedName(file.getOriginName())
                .path(file.getFileGroup().getDirectoryPath())
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

}
