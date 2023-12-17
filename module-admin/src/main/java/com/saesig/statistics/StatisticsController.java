package com.saesig.statistics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/user")
    public String userStatisticsView(Model model) {
        UserStatisticsDto userStatisticsDto = statisticsService.calculateUserStatistics();
        model.addAttribute("totalUserStatistics", userStatisticsDto);
        model.addAttribute("monthlyUserStatistics", userStatisticsDto.getMonthlyUserStatistics());
        return "statistics/user";
    }
}
