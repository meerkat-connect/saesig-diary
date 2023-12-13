package com.saesig.statistics;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatisticsMapper {
    UserStatisticsDto calculateTotalUserStatistics();

    List<MonthlyUserStatisticsDto> calculateMontlyUserStatistics();
}
