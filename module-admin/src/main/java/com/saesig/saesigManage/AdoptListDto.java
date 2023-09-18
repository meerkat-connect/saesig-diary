package com.saesig.saesigManage;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.common.mybatis.RequestDto;
import com.saesig.domain.adopt.AdoptStatus;
import com.saesig.domain.adopt.AdoptStopCategory;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AdoptListDto extends RequestDto {
    private Long id;
    private Long adoptMemberId;
    private String adoptMemberNickName;
    private String adoptMemberEmail;
    private Long hits;
    private String title;
    private String content;
    private String gender;
    private Integer age;
    private String ageCategory;
    private AdoptStatus status;
    private String isDeleted;
    private String isCastrated;
    private int responsibilityCost;
    private String etcContent;
    private Long animalDivision1Id;
    private Long animalDivision2Id;
    private String animalDivision1Category;
    private String animalDivision2Category;
    private Long imageFileGroupId;
    private String sido;
    private String sigungu;
    private String stopReason;
    private AdoptStopCategory stopCategory;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime modifiedAt;
    private Long modifiedBy;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    private Long createdBy;
    private String createdName;
    private String createdMail;
    private Long reportCnt;
    private Long chattingCnt;
    private Long likeCnt;
    private Integer[] vaccineList;
    private Long stopMemberId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime stopChangeCreatedAt;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime statusChangeCreatedAt;


    //searchParam
    private String searchKeyword;
    private String searchTitle;
    private String searchStatus;
    private String searchAnimalDivision1Category;
    private String searchAnimalDivision2Category;
    private String searchGender;
    private String searchReportCnt;
    private String searchIsDeleted;

}
