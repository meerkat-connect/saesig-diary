package com.saesig.statistics;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserStatisticsDto {
    private Integer registrationCount; // 회원가입 수
    private Integer leaveCount; // 탈퇴 수
    private Integer dormantCount; // 휴면 수
    private Integer normalCount; // 정상회원수 : 회원가입 수 - 탈퇴 - 휴면
    private Integer registrationDiffCount; // 전년대비 회원가입수 차이
    private Integer leaveDiffCount; // 전년대비 탈퇴회원수 차이
    private Integer dormantDiffCount; //전년대비 휴면회원수 차이
    private Integer normalDiffCount; // 전년대비 정상회원수 차이
    private List<MonthlyUserStatisticsDto> monthlyUserStatistics = new ArrayList<>();
}
