package com.saesig.config;

import com.amazonaws.services.s3.AmazonS3Client;
import com.saesig.global.file.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class FileServiceConfig {
    private final FileRepository fileRepository;
    private final FileGroupRepository fileGroupRepository;

    @Bean
    @Profile("local")
    public FileService localFileService() {
        return new LocalFileService(fileRepository, fileGroupRepository);
    }

    @Bean
    @Profile({"dev", "prod"})
    public FileService s3FileService(AmazonS3Client amazonS3Client) {
        return new S3FileService(amazonS3Client, fileRepository, fileGroupRepository);
    }

}
