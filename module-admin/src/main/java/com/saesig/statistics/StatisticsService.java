package com.saesig.statistics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StatisticsService {
    private final StatisticsMapper statisticsMapper;

    public UserStatisticsDto calculateUserStatistics() {
        return statisticsMapper.calculateUserStatistics();
    }

}
