package com.saesig.statistics;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StatisticsMapper {
    UserStatisticsDto calculateTotalUserStatistics(@Param("searchYear") Integer searchYear, @Param("prevSearchYear") Integer prevSearchYear);

    List<MonthlyUserStatisticsDto> calculateMonthlyUserStatistics(@Param("searchYear") Integer searchYear, @Param("prevSearchYear") Integer prevSearchYear);
}
