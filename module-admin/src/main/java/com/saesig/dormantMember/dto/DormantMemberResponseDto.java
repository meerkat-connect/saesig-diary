package com.saesig.dormantMember.dto;

import com.saesig.domain.member.MemberStatus;
import com.saesig.domain.member.SignupMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class DormantMemberResponseDto {
    private SignupMethod signupMethod;

    private String email;

    private String nickname;

    private MemberStatus status;

    private LocalDateTime joinedAt;

    private LocalDateTime lastLoggedAt;

    private LocalDateTime changedAt;
}
