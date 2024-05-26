package com.saesig.api.member.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class SignDto {
    private String mode; // 검색구분 이메일/닉네임

    private Long id;

    @NotEmpty
    @NotNull
    private String email;

    @NotEmpty
    private String password;

    private String prevPassword;

    private String signupMethod;

    private String nickname;

    private String mobileNumber;

    private String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastLoggedAt;

    private String serviceAgreement;

    private String locationServiceAgreement;

    private String privacyAgreement;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime passwordModifiedAt;

    private String marketingServiceAgreement;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime  deletedAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifiedAt;

    private Long modifiedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    private Long createdBy;

}
