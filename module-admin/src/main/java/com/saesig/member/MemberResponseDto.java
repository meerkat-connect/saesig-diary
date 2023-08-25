package com.saesig.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.domain.member.MemberStatus;
import com.saesig.domain.member.SignupMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String email;
    private String nickname;
    private SignupMethod signupMethod;
    private MemberStatus status;
    private int adoptCount;
    private int receiveCount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime createdAt;

}
