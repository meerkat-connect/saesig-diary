package com.saesig.statistics;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StatisticsMapper {
    UserStatisticsDto calculateTotalUserStatistics(@Param("searchYear") String searchYear);

    List<MonthlyUserStatisticsDto> calculateMontlyUserStatistics(@Param("searchYear") String searchYear);
}
