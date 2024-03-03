package com.saesig.statistics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/user/view")
    public String userStatisticsView() {
        return "statistics/user";
    }

    @GetMapping("/user/{searchYear}")
    @ResponseBody
    public Map<String, Object> findAll(@PathVariable String searchYear, Model model) {
        Map<String, Object> response = new HashMap<>();
        UserStatisticsDto userStatisticsDto = statisticsService.calculateUserStatistics(searchYear);

        response.put("totalUserStatistics", userStatisticsDto);
        response.put("monthlyUserStatistics", userStatisticsDto.getMonthlyUserStatistics());

        return response;
    }
}
