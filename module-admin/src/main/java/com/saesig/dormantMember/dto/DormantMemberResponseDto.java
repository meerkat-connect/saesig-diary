package com.saesig.dormantMember.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.domain.member.MemberStatus;
import com.saesig.domain.member.SignupMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class DormantMemberResponseDto {
    private Long id;

    private Long memberId;

    private SignupMethod signupMethod;

    private String email;

    private String nickname;

    private MemberStatus status;

    @JsonFormat(pattern = "YYYY-mm-dd")
    private LocalDateTime joinedAt;

    @JsonFormat(pattern = "YYYY-mm-dd")
    private LocalDateTime lastLoggedAt;

    @JsonFormat(pattern = "YYYY-mm-dd")
    private LocalDateTime changedAt;
}
