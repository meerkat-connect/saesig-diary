package com.saesig.report;

import com.saesig.global.enumCode.EnumMapperFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class ReportController {
    private final EnumMapperFactory enumMapperFactory;
    private final ReportService reportService;

    @GetMapping("/report/view")
    public String view(Model model) {
        model.addAttribute("reportCategory", enumMapperFactory.get("reportCategory"));
        return "report/view";
    }

}
