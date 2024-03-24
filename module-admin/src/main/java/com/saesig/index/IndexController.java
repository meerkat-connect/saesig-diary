package com.saesig.index;

import com.saesig.managerBoard.ManagerBoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class IndexController {
    private final DashBoardService dashBoardService;
    private final DashBoardMapper dashBoardMapper;

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String failureMessage, Model model) {
        model.addAttribute("failureMessage", failureMessage);

        return "common/login";
    }

    @GetMapping({"", "/"})
    public String index(DashBoardDto dashBoardDto, Model model) {
        return "common/index";
    }

    @GetMapping("/dashboardView")
    public String dashboardView(DashBoardDto dashBoardDto, Model model) {
        LocalDate today = LocalDate.now();
        String formattedDate = getFormattedDate(dashBoardDto.getPeriodType(), today);

        List<Map<String, Object>> adoptionStatistic = dashBoardMapper.countAdoptionStatus(dashBoardDto);
        List<Map<String, Object>> adoptionLocationStatistic = dashBoardMapper.countAdoptionLocationStatistic(dashBoardDto);
        List<ManagerBoardDto> adminPosts = dashBoardMapper.selectAdminPosts();
        Map<String, Object> registeredMembersCount = dashBoardMapper.countRegisteredMembers(dashBoardDto);
        Map<String, Object> reportStatistics = dashBoardMapper.countReports(dashBoardDto);
        Map<String, Object> inquiriesStatistics = dashBoardMapper.countInquiries(dashBoardDto);
        Map<String, Object> adoptionsStatistics = dashBoardMapper.countAdoptions(dashBoardDto);
        Map<String, Object> diarysStatistics = dashBoardMapper.countDiarys(dashBoardDto);
        List<Map<String, Object>> adoptionPosts = dashBoardMapper.selectAdoptions(); // 새로운 식구
        List<Map<String, Object>> diaryPosts = dashBoardMapper.selectDiarys(); // 일상 기록
        Integer smsDeliveryCount = dashBoardMapper.countSmsDelivery(dashBoardDto);
        Integer emailDeliveryCount = dashBoardMapper.countEmailDelivery(dashBoardDto);

        model.addAttribute("dashBoardDto", dashBoardDto);
        model.addAttribute("adoptionLocationStatistic", adoptionLocationStatistic);
        model.addAttribute("adoptionPosts", adoptionPosts);
        model.addAttribute("smsDeliveryCount", smsDeliveryCount);
        model.addAttribute("emailDeliveryCount", emailDeliveryCount);
        model.addAttribute("diaryPosts", diaryPosts);
        model.addAttribute("adminPosts", adminPosts);
        model.addAttribute("registeredMembersCount", registeredMembersCount);
        model.addAttribute("adoptionStatistic", adoptionStatistic);
        model.addAttribute("reportStatistics", reportStatistics);
        model.addAttribute("inquiriesStatistics", inquiriesStatistics);
        model.addAttribute("adoptionsStatistics", adoptionsStatistics);
        model.addAttribute("diarysStatistics", diarysStatistics);
        model.addAttribute("formattedDate", formattedDate);

        return "common/dashboard";
    }

    private String getFormattedDate(String periodType, LocalDate today) {
        String formattedDate = null;
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = today.get(weekFields.weekOfMonth());
        int month = today.getMonthValue();

        switch (periodType) {
            case "daily":
                formattedDate = today.format(DateTimeFormatter.ofPattern("yyyy. MM. dd"));
                break;
            case "weekly":
                formattedDate = String.format("%d월 %d주차", month, weekNumber);
                break;
            case "monthly":
                formattedDate = today.format(DateTimeFormatter.ofPattern("yyyy.M월"));
                break;
            case "totally":
                formattedDate = today.format(DateTimeFormatter.ofPattern("yyyy. MM. dd"));
                break;
        }
        return formattedDate;
    }

    @GetMapping("/reload")
    @ResponseBody
    public List<Map<String, Object>> reload(@RequestParam String type) {
        if ("adoption".equals(type)) {
            return dashBoardMapper.selectAdoptions();// 새로운 식구
        } else {
            return dashBoardMapper.selectDiarys(); // 일상 기록
        }
    }

    @GetMapping("/dashboard/search/adoption")
    @ResponseBody
    public Map<String,Object> searchAdoptionData(DashBoardDto dashBoardDto) {
        Map<String, Object> result = new HashMap<>();

        List<Map<String, Object>> adoptionStatistic = dashBoardMapper.countAdoptionStatus(dashBoardDto);
        List<Map<String, Object>> adoptionLocationStatistic = dashBoardMapper.countAdoptionLocationStatistic(dashBoardDto);

        result.put("adoptionStatistic", adoptionStatistic);
        result.put("adoptionLocationStatistic", adoptionLocationStatistic);

        return result;
    }

}
