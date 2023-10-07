package com.saesig.api.member.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class SignDto {

    private String mode; // 검색구분 이메일/닉네임

    private Long id;
    private String email;
    private String password;
    private String prevPassword;
    private String signupMethod;
    private String nickname;
    private String mobileNumber;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastLoggedAt;
    private String serviceAgreement;
    private String locationServiceAgreement;
    private String privacyAgreement;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime passwordModifiedAt;
    private String marketingServiceAgreement;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime  deletedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifiedAt;
    private Long modifiedBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;
    private Long createdBy;

}
