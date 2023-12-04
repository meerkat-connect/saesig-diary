package com.saesig.common.log;

import com.saesig.role.DataTablesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/access-log")
public class AdminAccessLogController {
    private final AdminAccessLogService adminAccessLogService;

    @GetMapping("/view")
    public String adminAccessLogView() {
        return "accessLog/view";
    }

    @GetMapping("")
    @ResponseBody
    public DataTablesResponseDto findAll(AdminAccessLogRequestDto adminAccessLogRequestDto) {
        Page<AdminAccessLogResponseDto> adminAccessLogs = adminAccessLogService.findAll(adminAccessLogRequestDto);
        return new DataTablesResponseDto(adminAccessLogs, adminAccessLogs.getContent());
    }
}
