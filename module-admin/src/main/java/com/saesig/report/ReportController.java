package com.saesig.report;

import com.saesig.global.enumCode.EnumMapperFactory;
import com.saesig.role.DataTablesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/admin/reports")
@Controller
public class ReportController {
    private final EnumMapperFactory enumMapperFactory;
    private final ReportService reportService;

    @GetMapping("/view")
    public String view(Model model) {
        model.addAttribute("reportCategory", enumMapperFactory.get("reportCategory"));
        return "reports/view";
    }

    @GetMapping("")
    @ResponseBody
    public DataTablesResponseDto findAll(@ModelAttribute ReportRequestDto requestDto) {
        return reportService.findAll(requestDto);
    }

    @GetMapping("/{reportId}")
    public String detail(Model model, @PathVariable Long reportId) {
        model.addAttribute("report", reportService.findById(reportId));
        model.addAttribute("adoptStatus", enumMapperFactory.get("adoptStatus"));
        model.addAttribute("diaryStatus", enumMapperFactory.get("diaryStatus"));
        return "reports/detail";
    }
}
