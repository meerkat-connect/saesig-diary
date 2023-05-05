package saesigDiary.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 1. Content-Type: applicattion/x-www-form-urlencoded
 * 2. Content-Type: multipart/form-data 방식 , enctype="multipart/form-data"
 */
@Service
public class FileService {
    @Value("${file.dir}")
    private String fileDir;

    /*
     * 첨부파일 업로드
     * 이미지파일 업로드
     * 이미지파일의 경우 웹 브라우저에서 확인 가능
     * */

    public String getPullPath(String fileName) {
        return fileDir + fileName;
    }

    public List<FileDto> storeFiles(List<MultipartFile> multipartFiles) throws Exception {
        List<FileDto> storeFileResult = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
            }
        }

        return storeFileResult;
    }

    public FileDto storeFile(MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty()) {
            return null;
        }

        String originFileName = multipartFile.getOriginalFilename();
        String savedFileName = createSavedFilleName(originFileName);

        multipartFile.transferTo(new File(getPullPath(savedFileName)));
        return new FileDto();
    }

    private String createSavedFilleName(String originFileName) {
        String ext = extractExt(originFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid + ext;
    }

    private String extractExt(String originFileName) {
        int index = originFileName.lastIndexOf(".");

        return originFileName.substring(index + 1);
    }
}
