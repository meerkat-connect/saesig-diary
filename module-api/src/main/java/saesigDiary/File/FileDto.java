package saesigDiary.File;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FileDto {
    private final String originName;
    private final String savedName;
    private final String path;

    @Builder
    public FileDto(String originName, String savedName, String path) {
        this.originName = originName;
        this.savedName = savedName;
        this.path = path;
    }
}
