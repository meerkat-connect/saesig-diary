package com.saesig.statistics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/user/view")
    public String userStatisticsView(Model model) {
        int currentYear = Year.now().getValue();
        List<Integer> years = IntStream.rangeClosed(currentYear - 3, currentYear)
                .boxed()
                .sorted((a, b) -> b.compareTo(a))
                .collect(Collectors.toList());

        model.addAttribute("years", years);
        return "statistics/user";
    }

    @GetMapping("/user/{searchYear}")
    @ResponseBody
    public Map<String, Object> findUserStatisticsByYear(@PathVariable Integer searchYear) {
        Map<String, Object> response = new HashMap<>();
        UserStatisticsDto userStatisticsDto = statisticsService.calculateUserStatistics(searchYear);

        response.put("totalUserStatistics", userStatisticsDto);
        response.put("monthlyUserStatistics", userStatisticsDto.getMonthlyUserStatistics());

        return response;
    }
}
