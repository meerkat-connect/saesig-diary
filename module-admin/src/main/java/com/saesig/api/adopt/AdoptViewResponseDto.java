package com.saesig.api.adopt;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.config.auth.SessionMember;
import com.saesig.domain.adopt.AdoptStatus;
import com.saesig.domain.adopt.AdoptStopCategory;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdoptViewResponseDto {
    private SessionMember member;

    private Long id;
    private Long adoptMemberId;
    private Long hits;
    private String title;
    private String content;
    private String gender;
    private Integer age;
    private String ageCategory;
    private AdoptStatus status;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime modifiedAt;
    private Long modifiedBy;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    private Long createdBy;
    private String createdName;
    private Long reportCnt;
    private Long chattingCnt;
    private Long likeCnt;
    private Integer[] vaccineList;
    private String changeMemberNickName;
    private String changeMemberEmail;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime stopChangeCreatedAt;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime statusChangeCreatedAt;
}
