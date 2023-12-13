package com.saesig.statistics;


import lombok.Getter;

@Getter
public class MonthlyUserStatisticsDto {
    private String yyyy;
    private String mm;
    private Integer normalCount;
    private Integer leaveCount;
    private Integer dormantCount;
}
