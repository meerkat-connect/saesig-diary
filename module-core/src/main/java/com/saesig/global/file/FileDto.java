package com.saesig.global.file;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FileDto {
    private final String originName;
    private final String savedName;
    private final String path;
    private final Long id;
    private final Long groupId;

    @Builder
    public FileDto(String originName, String savedName, String path, Long id, Long groupId) {
        this.originName = originName;
        this.savedName = savedName;
        this.path = path;
        this.id = id;
        this.groupId = groupId;
    }
}
