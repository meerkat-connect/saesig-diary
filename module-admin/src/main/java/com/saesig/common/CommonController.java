package com.saesig.common;

import com.saesig.domain.adopt.AdoptStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class CommonController {
    private final DashBoardService dashBoardService;
    private final DashBoardMapper dashBoardMapper;

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String failureMessage, Model model) {
        model.addAttribute("failureMessage", failureMessage);

        return "common/login";
    }

    @GetMapping({"", "/"})
    public String adminMain(DashBoardDto dashBoardDto, Model model) {
        dashBoardDto.setPeriodType("daily");
        dashBoardDto.setSearchAdoptionYear(2023);
        dashBoardDto.setSearchAdoptionStatus(AdoptStatus.COMPLETE.getValue());

        List<Map<String, Object>> maps = dashBoardMapper.selectAdoptions();
        List<Map<String, Object>> maps1 = dashBoardMapper.selectDiarys();
        List<Map<String, Object>> maps2 = dashBoardMapper.selectAdminPosts();
        List<Map<String, Object>> maps3 = dashBoardMapper.countRegisteredMembers(dashBoardDto);
        List<Map<String, Object>> maps4 = dashBoardMapper.countMessageDeliverys(dashBoardDto);
        List<Map<String, Object>> maps5 = dashBoardMapper.countAdoptionStatus(dashBoardDto);
        Map<String,Object> adoptionInfo = dashBoardMapper.selectAdoption(dashBoardDto);

        List<Integer> i = dashBoardMapper.countReports(dashBoardDto);
        List<Integer> i1 = dashBoardMapper.countInquiries(dashBoardDto);
        List<Integer> i2 = dashBoardMapper.countAdoptions(dashBoardDto);
        List<Integer> i3 = dashBoardMapper.countDiarys(dashBoardDto);

        model.addAttribute("adoptionInfo", adoptionInfo);
        model.addAttribute("maps", maps);
        model.addAttribute("maps1", maps1);
        model.addAttribute("maps2", maps2);
        model.addAttribute("maps3", maps3);
        model.addAttribute("maps4", maps4);
        model.addAttribute("maps5", maps5);
        model.addAttribute("i", i);
        model.addAttribute("i1", i1);
        model.addAttribute("i2", i2);
        model.addAttribute("i3", i3);

        return "common/main";
    }
}
