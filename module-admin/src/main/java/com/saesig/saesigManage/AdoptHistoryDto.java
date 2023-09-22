package com.saesig.saesigManage;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.common.mybatis.RequestDto;
import com.saesig.config.auth.SessionMember;
import com.saesig.domain.adopt.AdoptStatus;
import com.saesig.domain.adopt.AdoptStopCategory;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AdoptHistoryDto extends RequestDto {
    private Long id;
    private Long adoptId;
    private AdoptStatus beforeStatus;
    private AdoptStatus afterStatus;
    private String reason;
    private Long memberId;
    private String nickname;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime modifiedAt;
    private Long modifiedBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    private Long createdBy;
}
