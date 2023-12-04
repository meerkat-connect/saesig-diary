package com.saesig.common.log;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Service
public class AdminAccessLogService {
    private final AdminAccessLogRepository adminAccessLogRepository;
    public void insertLog(AdminAccessLogResponseDto adminAccessLogResponseDto){
        adminAccessLogRepository.insertLog(adminAccessLogResponseDto);
    }

    @ResponseBody
    public Page<AdminAccessLogResponseDto> findAll(AdminAccessLogRequestDto adminAccessLogRequestDto) {
        return adminAccessLogRepository.findAll(adminAccessLogRequestDto);
    }
}
