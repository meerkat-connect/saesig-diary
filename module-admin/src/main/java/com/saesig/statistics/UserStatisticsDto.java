package com.saesig.statistics;

import lombok.Getter;

@Getter
public class UserStatisticsDto {
    private Integer registrationCount; // 회원가입 수
    private Integer leaveCount; // 탈퇴 수
    private Integer dormantCount; // 휴면 수
    private Integer normalCount; // 정상회원수 : 회원가입 수 - 탈퇴 - 휴면
    private Integer registrationDiffCount;
    private Integer leaveDiffCount;
    private Integer dormantDiffCount;
    private Integer normalDiffCount;
}
