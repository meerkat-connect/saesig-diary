package com.saesig.common.log;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/access-log")
public class AdminAccessLogController {
    private final AdminAccessLogService adminAccessLogService;

    @GetMapping("/view")
    public String adminAccessLogView() {
        return "log/view";
    }

    @GetMapping("")
    @ResponseBody
    public List<AdminAccessLogResponseDto> findAll() {
        return adminAccessLogService.findAll();
    }
}
