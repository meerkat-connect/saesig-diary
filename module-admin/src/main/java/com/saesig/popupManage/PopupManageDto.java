package com.saesig.popupManage;

import com.saesig.common.mybatis.RequestDto;
import com.saesig.config.auth.SessionMember;
import com.saesig.global.file.FileDto;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PopupManageDto extends RequestDto {

    private SessionMember member;

    private List<PopupManageDto> pmdList;

    private Long id;
    private String title;
    private String exposureLocation;
    private String target;
    private String targetRoleId;
    private String startDate;
    private String endDate;
    private Long imageFileGroupId = Long.valueOf(0);
    private String url;
    private String buttonOption;
    private Long ord;
    private Character isEnabled;
    private String createdAt;
    private String createdBy;
    private String modifiedAt;
    private String modifiedBy;

    private FileDto fileDto;
    private String savedName;
    private MultipartFile popupFile;

    // search
    // 제목, 팝업위치
    private String searchTitle;
    private String searchExposureLocation;
}
