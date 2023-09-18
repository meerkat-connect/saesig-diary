package com.saesig.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.domain.member.MemberStatus;
import com.saesig.domain.member.SignupMethod;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberDetailResponseDto {
    private String nickname;

    private String email;

    private SignupMethod signupMethod;

    private MemberStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoggedAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dormancyConvertedAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deletedAt;
}
