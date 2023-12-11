package com.saesig.statistics;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatisticsMapper {
    UserStatisticsDto calculateUserStatistics();
}
