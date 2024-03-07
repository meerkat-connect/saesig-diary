package com.saesig.statistics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StatisticsService {
    private final StatisticsMapper statisticsMapper;

    public UserStatisticsDto calculateUserStatistics(Integer searchYear) {
        UserStatisticsDto userStatistics = statisticsMapper.calculateTotalUserStatistics(searchYear, searchYear - 1);
        userStatistics.setMonthlyUserStatistics(statisticsMapper.calculateMonthlyUserStatistics(searchYear, searchYear - 1));
        return userStatistics;
    }

}
