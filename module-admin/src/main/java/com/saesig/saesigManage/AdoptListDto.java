package com.saesig.saesigManage;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.domain.adopt.AdoptStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AdoptListDto {
    private Long id;
    private Long adoptMemberId;
    private Long hits;
    private String title;
    private String content;
    private String gender;
    private Integer age;
    private String ageCategory;
    private AdoptStatus status;
    private String isDeleted;
    private String isCastrated;
    private BigDecimal responsibilityCost;
    private String etcContent;
    private Long animalDivision1Id;
    private Long animalDivision2Id;
    private String animalDivision1Category;
    private String animalDivision2Category;
    private Long imageFileGroupId;
    private String sido;
    private String sigungu;
    private String stopReason;
    private String stopCategory;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime modifiedAt;
    private Long modifiedBy;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    private Long createdBy;
    private Long reportCnt;
    private Long chattingCnt;
    private Long likeCnt;
    private Long hit;
}
