package com.saesig.banner;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.common.mybatis.RequestDto;
import com.saesig.config.auth.SessionMember;
import com.saesig.global.file.FileDto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BannerDto extends RequestDto {

    private SessionMember member;

    private List<BannerDto> bdList;

    private Long id;
    private String title;
    private String exposureLocation;
    private Long imageFileGroupId = Long.valueOf(0);
    private String url;
    private Long ord;
    private String isEnabled;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    private String createdBy;
    private String modifiedAt;
    private String modifiedBy;

    private FileDto fileDto;
    private String savedName;
    private MultipartFile bannerFile;

    // search
    // 제목, 배너위치
    private String searchTitle;
    private String searchExposureLocation;
}
