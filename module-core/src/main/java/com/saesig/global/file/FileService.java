package com.saesig.global.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface FileService {
    String getFullPath(String fileName);

    List<FileDto> storeFiles(List<MultipartFile> multipartFiles);

    FileDto storeFile(MultipartFile multipartFile);

    FileDto findBySavedName(String savedFileName);

    default String createSavedFileName(String originFileName) {
        return UUID.randomUUID() + "." + extractExt(originFileName);
    }

    default String extractExt(String originFileName) {
        return originFileName.substring(originFileName.lastIndexOf(".") + 1);
    }
}
