package com.saesig.api.adopt;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.config.auth.SessionMember;
import com.saesig.domain.adopt.AdoptStatus;
import com.saesig.domain.adopt.AdoptStopCategory;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdoptListResponseDto {
        private Long id;
        private Long hits;
        private String title;
        private String content;
        private AdoptStatus status;
        private Long imageFileGroupId;
        private String sido;
        private String sigungu;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDateTime modifiedAt;
        private Long modifiedBy;
        private Long chattingCnt;
        private Long likeCnt;
}
