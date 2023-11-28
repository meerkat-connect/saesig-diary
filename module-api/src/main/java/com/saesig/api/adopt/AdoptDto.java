package com.saesig.api.adopt;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.config.auth.SessionMember;
import com.saesig.domain.adopt.AdoptStatus;
import com.saesig.domain.adopt.AdoptStopCategory;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdoptDto {
        private SessionMember member;

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
        private AdoptStatus beforeStatus;
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
        private LocalDateTime modifiedAt;
        private Long modifiedBy;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDateTime createdAt;
        private Long createdBy;
        private String createdName;
        private String createdMail;
        private Long reportCnt;
        private Long chattingCnt;
        private Long likeCnt;
        private Integer[] vaccineList;
        private Long stopMemberId;
        private String changeMemberNickName;
        private String changeMemberEmail;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDateTime stopChangeCreatedAt;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDateTime statusChangeCreatedAt;


        //searchParam
        private String searchKeyword;
        private String searchType;
        private String searchTitle;
        private String searchStatus;
        private String searchAnimalDivision1Category;
        private String searchAnimalDivision2Category;
        private String searchGender;
        private String searchReportCnt;
        private String searchIsDeleted;


}
