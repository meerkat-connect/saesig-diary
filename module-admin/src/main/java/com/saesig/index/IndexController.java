package com.saesig.index;

import com.saesig.domain.adopt.AdoptStatus;
import com.saesig.managerBoard.ManagerBoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
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
        dashBoardDto.setSearchAdoptionYear(today.getYear());
        dashBoardDto.setSearchAdoptionStatus(AdoptStatus.COMPLETE.getKey());

        List<Map<String, Object>> adoptionStatistic = dashBoardMapper.countAdoptionStatus(dashBoardDto);

//        Map<String,Object> adoptionInfo = dashBoardMapper.selectAdoption(dashBoardDto);

        List<ManagerBoardDto> adminPosts = dashBoardMapper.selectAdminPosts();
        Map<String, Object> registeredMembersCount = dashBoardMapper.countRegisteredMembers(dashBoardDto);
        Map<String, Object> reportStatistics = dashBoardMapper.countReports(dashBoardDto);
        Map<String, Object> inquiriesStatistics = dashBoardMapper.countInquiries(dashBoardDto);
        Map<String, Object> adoptionsStatistics = dashBoardMapper.countAdoptions(dashBoardDto);
        Map<String, Object> diarysStatistics = dashBoardMapper.countDiarys(dashBoardDto);
        List<Map<String, Object>> adoptions = dashBoardMapper.selectAdoptions();
        List<Map<String, Object>> diarys = dashBoardMapper.selectDiarys();
        Integer smsDeliveryCount = dashBoardMapper.countSmsDelivery(dashBoardDto);
        Integer emailDeliveryCount = dashBoardMapper.countEmailDelivery(dashBoardDto);

        //        model.addAttribute("adoptionInfo", adoptionInfo);
        model.addAttribute("adoptions", adoptions);
        model.addAttribute("smsDeliveryCount", smsDeliveryCount);
        model.addAttribute("emailDeliveryCount", emailDeliveryCount);
        model.addAttribute("diarys", diarys);
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
                formattedDate = String.format("%02d월 %d주차", month, weekNumber);
                break;
            case "monthly":
                formattedDate = today.format(DateTimeFormatter.ofPattern("yyyy. MM월"));
                break;
            case "totally":
                formattedDate = today.format(DateTimeFormatter.ofPattern("yyyy. MM. dd"));
                break;
        }
        return formattedDate;
    }
}
