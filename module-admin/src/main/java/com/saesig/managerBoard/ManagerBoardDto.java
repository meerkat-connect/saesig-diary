package com.saesig.managerBoard;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.common.mybatis.RequestDto;
import com.saesig.config.auth.SessionMember;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ManagerBoardDto extends RequestDto {

    private SessionMember member;

    private Long id;
    private Long[] ids;
    private String title;
    private String content;
    private String category;
    private String hits;
    private char isDeleted;
    private String nickName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    private String createdBy;
    private String modifiedAt;
    private String modifiedBy;

    // search
    // 키워드, 유형, 제목, 등록일
    private String searchType;
    private String searchKeyword;
    private String searchCategory;
    private String searchTitle;
    private String searchBgngDt;
    private String searchEndDt;

}
