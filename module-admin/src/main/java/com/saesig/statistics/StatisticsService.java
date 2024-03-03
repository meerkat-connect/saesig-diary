package com.saesig.statistics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StatisticsService {
    private final StatisticsMapper statisticsMapper;

    public UserStatisticsDto calculateUserStatistics(String searchYear) {
        UserStatisticsDto userStatistics = statisticsMapper.calculateTotalUserStatistics(searchYear);
        userStatistics.setMonthlyUserStatistics(statisticsMapper.calculateMontlyUserStatistics(searchYear));
        return userStatistics;
    }

}
