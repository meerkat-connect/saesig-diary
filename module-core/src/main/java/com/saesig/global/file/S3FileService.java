package com.saesig.global.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.saesig.error.ErrorCode;
import com.saesig.error.FileNotExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class S3FileService implements FileService {

    private final AmazonS3 s3Client;

    private final FileRepository fileRepository;

    private final FileGroupRepository fileGroupRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String getFullPath(String fileName) {
        return "";
    }

    @Override
    @Transactional
    public List<FileDto> storeFiles(List<MultipartFile> multipartFiles) {
        ArrayList<FileDto> storedFiles = new ArrayList<>();

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
            File uploadFile = convert(multipartFile)
                    .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));

            String fileName = multipartFile.getOriginalFilename();
            String uploadImageUrl = putS3(uploadFile, fileName);
            removeNewFile(uploadFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
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

    private String putS3(File uploadFile, String fileName) {
        s3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return s3Client.getUrl(bucket, fileName).toString();
    }

    // 서버 로컬에 저장된 파일 제거
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일 삭제에 실패하였습니다.");
        }
    }

    // S3 전달을 위해 MultipartFile을 File로 변환
    private Optional<File> convert(MultipartFile multipartFile) throws IOException {
        File convertFile = new File(multipartFile.getOriginalFilename());

        if (convertFile.createNewFile()) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(convertFile)) {
                fileOutputStream.write(multipartFile.getBytes());
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }
}
