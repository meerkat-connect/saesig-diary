package com.saesig.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.common.RequestDto;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class MemberDto extends RequestDto {

    private Long id;
    private String email;
    private String password;
    private String signupMethod;
    private String nickname;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime joinedAt;
    private String modifiedAt;
    private String modifiedBy;
    private String createdAt;
    private String createdBy;
    private String serviceAgreement;
    private String privacyAgreement;
    private String locationServiceAgreement;
    private String marketingServiceAgreement;
}
