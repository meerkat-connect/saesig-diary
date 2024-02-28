package com.saesig.statistics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/user/view")
    public String userStatisticsView(Model model) {
        UserStatisticsDto userStatisticsDto = statisticsService.calculateUserStatistics();
        model.addAttribute("totalUserStatistics", userStatisticsDto);
        model.addAttribute("monthlyUserStatistics", userStatisticsDto.getMonthlyUserStatistics());
        return "statistics/user";
    }

    @GetMapping("/user")
    @ResponseBody
    public UserStatisticsDto findAll(@ModelAttribute UserStatisticsDto userStatisticsDto) {
        return statisticsService.calculateUserStatistics();
    }
}
