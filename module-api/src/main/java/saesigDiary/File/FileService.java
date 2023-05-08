package saesigDiary.File;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import saesigDiary.domain.FileGroup;

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

    private final FileRepository fileRepository;

    private final FileGroupRepository fileGroupRepository;

    public String getPullPath(String fileName) {
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

        saesigDiary.domain.File savedFile = fileRepository.save(saesigDiary.domain.File.builder()
                                                                        .fileGroup(savedFileGroup)
                                                                        .originName(originFileName)
                                                                        .savedName(savedFileName)
                                                                        .size(multipartFile.getSize())
                                                                        .extension(extractExt(originFileName))
                                                                        .build());
        multipartFile.transferTo(new File(getPullPath(savedFileName)));

        return FileDto.builder()
                .savedName(savedFile.getSavedName())
                .originName(savedFile.getOriginName())
                .path(savedFileGroup.getDirectoryPath())
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
